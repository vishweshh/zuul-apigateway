package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.filters.pre.PreFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration
public class ApigatewayApplication {

	public static void main(String[] args) {
		System.out.println("------------------------------------------------ZUUL MAIN--------------------------------");
		System.out.println("------------------------------------------------ZUUL MAIN--------------------------------");
		System.out.println("------------------------------------------------ZUUL MAIN--------------------------------");
		System.out.println("------------------------------------------------ZUUL MAIN--------------------------------");
		System.out.println("------------------------------------------------ZUUL MAIN--------------------------------");
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
}
