package dev.miracode.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "dev.miracode.sms.client")
public class SmsGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsGatewayApplication.class, args);
  }

}
