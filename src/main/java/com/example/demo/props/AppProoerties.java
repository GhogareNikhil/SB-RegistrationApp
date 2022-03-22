package com.example.demo.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "reg-app")
public class AppProoerties {

	private Map<String, String> message= new HashMap<>();
}
