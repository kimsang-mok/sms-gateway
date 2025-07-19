package dev.miracode.sms.config.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    return switch (response.status()) {
      case 400 -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request from remote service");
      case 404 -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found from remote service");
      case 500 -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Remote Server Error");
      default -> new Exception("Generic error from Feign client: " + response.reason());
    };
  }
}
