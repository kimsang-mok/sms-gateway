package dev.miracode.sms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendSmsRequestDto {
  @NotBlank(message = "Phone number is required")
  private String phoneNumber;

  @NotBlank(message = "Message is required")
  private String message;
}
