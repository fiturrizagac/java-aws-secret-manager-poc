package net.befitca.poc.aws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
@EnableConfigurationProperties
public class SecretManagerBootstrapConfiguration {

    @Bean
    public PropertySourceLocator myPropertySourceLocator() {

        return new SMPropertySourceLocator();


    }

    @Order(1)
    public static class SMPropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {
            CompositePropertySource composite = new CompositePropertySource("secretmanagerpr");

            Map<String,Object> map = new HashMap<>();
            map.put("com.befitca.poc.secret1", "valor que necesito bootstrap");
            composite.addPropertySource(
                new MapPropertySource("bootstrap-secret-manager", map));



            return composite;
        }
    }

}




