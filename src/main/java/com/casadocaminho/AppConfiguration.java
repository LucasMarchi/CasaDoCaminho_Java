package com.casadocaminho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {AppConfiguration.class, Jsr310JpaConverters.class})
@SpringBootApplication
@EnableCaching
public class AppConfiguration {
	
	public static void main(String[] args){
        SpringApplication.run(AppConfiguration.class, args);
    }

}
