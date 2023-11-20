package com.example.newforum.entity;

import com.example.newforum.Dto.PostAddRequestDto;
import com.example.newforum.Dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, length = 20)
    private String author;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 1000)
    private String contents;

    public PostEntity(PostAddRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContent();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContent();
    }

    public boolean matchPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
