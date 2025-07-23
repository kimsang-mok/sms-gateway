package dev.miracode.sms.application;

import dev.miracode.sms.domain.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final SmsSender smsSender;

  public void send(String phoneNumber, String message) {
    smsSender.sendSms(phoneNumber, message);
  }
}
