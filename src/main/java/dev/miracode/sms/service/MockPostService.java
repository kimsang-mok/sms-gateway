package dev.miracode.sms.service;

import dev.miracode.sms.client.JsonPlaceholderClient;
import dev.miracode.sms.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MockPostService {
  private final JsonPlaceholderClient jsonPlaceholderClient;

  public List<PostDto> fetchMockPosts() {
    return jsonPlaceholderClient.getPosts();
  }
}
