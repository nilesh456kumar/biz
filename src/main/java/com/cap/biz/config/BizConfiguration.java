package com.cap.biz.config;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.config.RepositoryConfigurationUtils;
import org.springframework.web.client.RestTemplate;

import com.cap.biz.repositoryImpl.ProductNotFoundException;


@Configuration
public class BizConfiguration {
	
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();   
	   }

	 @Bean
	 public ProductNotFoundException geProductNotFoundException()
	 {
		 return new ProductNotFoundException("invalid product id");
	 }
}
