package com.example.practiceboard.service;

import com.example.practiceboard.domain.Post;
import com.example.practiceboard.dto.post.PostReqDTO;
import com.example.practiceboard.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class PostServiceTest {
    private final PostRepository postRepository = Mockito.mock(PostRepository.class);
    private final PostService postService = new PostServiceImpl(postRepository);

    @Test
    @DisplayName("게시글 작성")
    void create() {
        String author = "작성자";
        String title = "제목";
        String contents = "본문";
        String password = "password";
        PostReqDTO dto = PostReqDTO.builder()
                .author(author)
                .title(title)
                .contents(contents)
                .password(password)
                .build();
        Mockito.when(postRepository.save(any(Post.class))).thenReturn(dto.toEntity());
        Post saved = postService.create(dto);

        assertEquals(author, saved.getAuthor());
        assertEquals(title, saved.getTitle());
        assertEquals(contents, saved.getContents());
        assertEquals(password, saved.getPassword());
        Mockito.verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 삭제 : 해당 게시글이 없는 경우 RuntimeException")
    void deleteByIdWhenNoContents() {
        given(postRepository.findById(100)).willReturn(Optional.empty());
        assertThrows(RuntimeException.class,() -> postService.deleteById(100, any(String.class)));
    }

    @Test
    @DisplayName("게시글 삭제 : 패스워드 불일치 RuntimeException")
    void deleteByIdWhenWrongPassword() {
        Post post = new Post("작성자", "제목", "본문", "password");
        given(postRepository.findById(1)).willReturn(Optional.of(post));
        assertThrows(RuntimeException.class, () -> postService.deleteById(1, "1234"));
    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteById() {
        Post post = new Post("작성자", "제목", "본문", "password");
        given(postRepository.findById(1)).willReturn(Optional.of(post));
        postService.deleteById(1, post.getPassword());
    }
}