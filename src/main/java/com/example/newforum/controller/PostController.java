package com.example.newforum.controller;

import com.example.newforum.Dto.PostAddRequestDto;
import com.example.newforum.Dto.PostResponseDto;
import com.example.newforum.Dto.PostUpdateRequestDto;
import com.example.newforum.service.PostService;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController<requestDto> {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto addPost(
            @RequestBody PostAddRequestDto requestDto
            ) {
        PostResponseDto responseDto = postService.addPost(requestDto);
        return responseDto;
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPost(
            @PathVariable Long postId
    ) {
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PatchMapping("/{postId}")
    public PostResponseDto updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto requestDto
    ) {
        return postService.upstePost(postId, requestDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(
            @PathVariable Long postId,
            @RequestHeader("password") String password
    ) {
        postService.deletePost(postId, password);
    }
}
