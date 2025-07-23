package dev.miracode.sms.infrastructure.twilio.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class TwilioFeignConfig {
  @Value("${twilio.accountSid}")
  private String accountSid;

  @Value("${twilio.authToken}")
  private String authToken;

  @Bean
  public RequestInterceptor twilioRequestInterceptor() {
    return template -> {
      String basicAuth = Base64.getEncoder()
          .encodeToString((accountSid + ":" + authToken).getBytes());

      template.header("Authorization", "Basic " + basicAuth);
    };
  }

  @Bean
  public ErrorDecoder twilioErrorDecoder() {
    return (methodKey, response) ->
        new RuntimeException("Twilio error: " + response.status() + " " + response.reason());
  }
}
