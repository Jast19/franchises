package co.com.nequi.franchises.franchise.adapter.in.api.config;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ApiConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
