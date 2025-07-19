package dev.miracode.sms.controller;

import dev.miracode.sms.dto.PostDto;
import dev.miracode.sms.service.MockPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MockPostController {
  private final MockPostService mockPostService;

  @GetMapping("/mock-posts")
  public List<PostDto> getMockPosts() {
    return mockPostService.fetchMockPosts();
  }
}
