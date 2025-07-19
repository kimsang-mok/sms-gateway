package dev.miracode.sms.client;

import dev.miracode.sms.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "jsonPlaceholderClient", url = "${jsonplaceholder.base-url}")
public interface JsonPlaceholderClient {
  @GetMapping("/posts")
  List<PostDto> getPosts();
}
