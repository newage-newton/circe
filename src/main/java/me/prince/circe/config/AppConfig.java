package me.prince.circe.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "me.prince.circe")
@PropertySource("classpath:/me/prince/circe/config/app.properties")
public class AppConfig {
}
