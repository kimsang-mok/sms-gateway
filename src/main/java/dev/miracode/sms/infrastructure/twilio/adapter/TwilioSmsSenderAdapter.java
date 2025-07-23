package dev.miracode.sms.infrastructure.twilio.adapter;

import dev.miracode.sms.domain.SmsSender;
import dev.miracode.sms.infrastructure.twilio.client.TwilioSmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("twilio")
public class TwilioSmsSenderAdapter implements SmsSender {
  private final TwilioSmsClient twilioSmsClient;

  @Value("${twilio.accountSid}")
  private String accountSid;

  @Value("${twilio.sms.fromNumber}")
  private String fromNumber;

  @Override
  public void sendSms(String phoneNumber, String message) {
    try {
      twilioSmsClient.sendMessage(
          accountSid,
          phoneNumber,
          fromNumber,
          message
      );
      log.info("Sent SMS to {} via Twilio", phoneNumber);
    } catch (Exception e) {
      log.error("Failed to send SMS via Twilio", e);
      throw new RuntimeException("Twilio SMS failed", e);
    }
  }
}
