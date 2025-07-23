package dev.miracode.sms.domain;

public interface SmsSender {
  void sendSms(String phoneNumber, String messageText);
}
