package com.example.newforum.service;

import com.example.newforum.Dto.PostAddRequestDto;
import com.example.newforum.Dto.PostResponseDto;
import com.example.newforum.Dto.PostUpdateRequestDto;
import com.example.newforum.entity.PostEntity;
import com.example.newforum.repository.PostJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostResponseDto addPost(PostAddRequestDto requestDto) {
        // dto를 entity로 만들기
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePost = postJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        return new PostResponseDto(postEntity);
    }

    public List<PostResponseDto> getPosts() {
        return postJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto upstePost(Long postId, PostUpdateRequestDto requestDto) {
        PostEntity postEntity = getPostEntity(postId);

        verifyPassword(postEntity, requestDto.getPassword());
        postEntity.update(requestDto);

        return new PostResponseDto(postEntity);
    }

    public void deletePost(Long postId, String password) {
        PostEntity postEntity = getPostEntity(postId);
        verifyPassword(postEntity, password);
        postJpaRepository.delete(postEntity);
    }

    private PostEntity getPostEntity(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }

    private static void verifyPassword(PostEntity postEntity, String password) {
        if (!postEntity.matchPassword(password)) {
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }
    }
}

