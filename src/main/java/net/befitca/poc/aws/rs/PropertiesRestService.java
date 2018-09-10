package net.befitca.poc.aws.rs;

import java.util.Arrays;

import net.befitca.poc.aws.rs.om.CollectionResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class PropertiesRestService {


    @Value("${com.befitca.poc.secret1}")
    private String key1;
    @Value("${com.befitca.poc.secret2}")
    private String key2;

    @GetMapping("/")
    public ResponseEntity<CollectionResponse> findAll() {

        return ResponseEntity.ok(
            new CollectionResponse(
                Arrays.asList(
                    String.format("com.befitca.poc.secret1:%s",key1),
                    String.format("com.befitca.poc.secret2:%s",key2)
                )
            ));
    }

}
