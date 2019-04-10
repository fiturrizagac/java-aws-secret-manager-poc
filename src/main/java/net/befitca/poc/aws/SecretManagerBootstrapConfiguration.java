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
import org.springframework.core.env.StandardEnvironment;

@Configuration
@EnableConfigurationProperties
public class SecretManagerBootstrapConfiguration {

//    @Bean
//    public PropertySourceLocator myPropertySourceLocator() {
//
//        return new SMPropertySourceLocator();
//
//
//    }

    @Bean
    public PropertySourceLocator myOtherPropertySourceLocator() {

        return new SMP2PropertySourceLocator();


    }

//    @Bean
//    public PropertySourceLocator myOther2PropertySourceLocator() {
//
//        return new SMPEnvPropertySourceLocator();
//
//
//    }

    @Order(-4)
    public static class SMPropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {
            CompositePropertySource composite = new CompositePropertySource("secretmanagerpr");

            Map<String,Object> map = new HashMap<>();
            map.put("com.befitca.poc.secret1", "secretmanager");
            composite.addPropertySource(
                new MapPropertySource("bootstrap-secret-manager", map));

            return composite;
        }
    }

    @Order(-2)
    public static class SMP2PropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {

            CompositePropertySource composite = new CompositePropertySource("bootstrap-otro");

            Map<String,Object> map = new HashMap<>();
            map.put("com.befitca.poc.secret1", "bootstrapotro");
            composite.addPropertySource(
                new MapPropertySource("bootstrap-otro", map));

            return composite;
        }

    }

    @Order(-3)
    public static class SMPEnvPropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {

            Map<String,Object> values = ((StandardEnvironment)environment).getSystemEnvironment();



            CompositePropertySource composite = new CompositePropertySource("newenvotro");

            //values.put("com.befitca.poc.secret2", "gano el boostrap ps");
            composite.addPropertySource(
                new MapPropertySource("newenvotro", values));

            return composite;
        }
    }

}




