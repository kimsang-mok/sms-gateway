package dev.miracode.sms.infrastructure.twilio.client;

import dev.miracode.sms.infrastructure.twilio.config.TwilioFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "twilioSmsClient",
    url = "${twilio.sms.baseUrl}",
    configuration = TwilioFeignConfig.class
)
public interface TwilioSmsClient {

  @PostMapping(value = "/2010-04-01/Accounts/{accountSid}/Messages.json",
      consumes = "application/x-www-form-urlencoded")
  void sendMessage(
      @PathVariable("accountSid") String accountSid,
      @RequestParam("To") String to,
      @RequestParam("From") String from,
      @RequestParam("Body") String body
  );
}
