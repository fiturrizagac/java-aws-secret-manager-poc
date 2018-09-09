package net.befitca.aws.poc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

public class PocEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Map<String,Object> map = new HashMap<>();
        map.put("com.befitca.poc.secret1", "valor que necesito");


        environment.getPropertySources().addFirst(new MapPropertySource("secret-manager",map));


        System.out.println("franco " + environment.getPropertySources().size());

        environment.getPropertySources().iterator().forEachRemaining(propertySource -> {
            System.out.println("franco name " + propertySource.getName());
            System.out.println("franco valor " + propertySource.getSource());
            System.out.println("franco class " + propertySource.getClass());
            System.out.println();

        });

    }
}
