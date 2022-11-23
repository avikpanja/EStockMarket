package com.estock.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.estock.company.filter.RequestFilter;
import com.estock.company.service.KafkaConsumer;

@SpringBootApplication
@EnableEurekaClient
public class CompanyManagementApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		FilterRegistrationBean fb = new FilterRegistrationBean<>();
		fb.setFilter(new RequestFilter());
		fb.addUrlPatterns("/api/v1.0/*");
		return fb;
	}

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementApplication.class, args);
	}
}
