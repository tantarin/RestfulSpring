package com.example.rest.config;

import com.example.rest.beans.Person;
import com.example.rest.service.StoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name="store")
    public StoreService storeService() {
        return new StoreService();
    }

    @Bean
    @Scope("singleton")
    public Person personSingleton() {
        return new Person();
    }
}
