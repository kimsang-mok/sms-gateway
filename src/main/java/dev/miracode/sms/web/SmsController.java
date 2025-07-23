package dev.miracode.sms.web;

import dev.miracode.sms.application.NotificationService;
import dev.miracode.sms.dto.SendSmsRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {
  private final NotificationService service;

  @PostMapping("/send")
  public ResponseEntity<Void> sendSms(@Valid @RequestBody SendSmsRequestDto request) {
    service.send(request.getPhoneNumber(), request.getMessage());
    return ResponseEntity.noContent().build();
  }
}
