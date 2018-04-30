package com.rookiego.www.wechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:spring/elastic-job-spring.xml"})
public class ElasticJobConfig {
}
