package dev.miracode.sms.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "dev.miracode.sms.infrastructure.twilio.client")
public class FeignConfig {
}
