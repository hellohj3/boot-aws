package org.example.bootaws.web;

import lombok.RequiredArgsConstructor;
import org.example.bootaws.sevice.posts.PostsService;
import org.example.bootaws.web.dto.PostsResponseDto;
import org.example.bootaws.web.dto.PostsSaveRequestDto;
import org.example.bootaws.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
