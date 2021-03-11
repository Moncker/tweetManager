package com.example.tweetManager.tweetManager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.example.tweetManager.tweetManager.service.TwitterThread")
public class AppConfig{
}