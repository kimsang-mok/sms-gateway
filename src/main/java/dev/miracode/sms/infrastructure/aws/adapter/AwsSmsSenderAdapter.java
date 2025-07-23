package dev.miracode.sms.infrastructure.aws.adapter;

import dev.miracode.sms.domain.SmsSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpointsmsvoicev2.PinpointSmsVoiceV2Client;
import software.amazon.awssdk.services.pinpointsmsvoicev2.model.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("aws")
public class AwsSmsSenderAdapter implements SmsSender {

  @Value("${aws.sms.originationIdentityPoolArn}")
  private String originationPoolArn;

  @Value("${aws.region}")
  private String awsRegion;

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretAccessKey;

  @Value("${aws.sms.configurationSetName}")
  private String configurationSetName;


  @Override
  public void sendSms(String phoneNumber, String messageText) {
    try (PinpointSmsVoiceV2Client client = PinpointSmsVoiceV2Client.builder()
        .region(Region.of(awsRegion))
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessKey, secretAccessKey)
        ))
        .build()) {

      SendTextMessageRequest request = SendTextMessageRequest.builder()
          .destinationPhoneNumber(phoneNumber)
          .originationIdentity(originationPoolArn)
          .messageBody(messageText)
          .messageType(MessageType.TRANSACTIONAL)
          .configurationSetName(configurationSetName)
          .build();

      SendTextMessageResponse response = client.sendTextMessage(request);

      log.info("Sent SMS to {} (MessageId={})", phoneNumber, response.messageId());
    } catch (PinpointSmsVoiceV2Exception ex) {
      log.error("Failed to send SMS to {}: {}", phoneNumber, ex.awsErrorDetails().errorMessage(), ex);
      throw ex;
    }
  }
}
