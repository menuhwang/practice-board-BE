package com.example.practiceboard.controller;

import com.example.practiceboard.domain.Post;
import com.example.practiceboard.dto.post.PostDelReqDTO;
import com.example.practiceboard.dto.post.PostReqDTO;
import com.example.practiceboard.dto.post.PostResDTO;
import com.example.practiceboard.dto.post.PostResWithDetailDTO;
import com.example.practiceboard.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<PostResDTO> create(@RequestBody PostReqDTO dto) {
        Post post = postService.create(dto);
        return ResponseEntity.ok(PostResDTO.builder()
                        .id(post.getId())
                        .author(post.getAuthor())
                        .title(post.getTitle())
                        .build());
    }

    @GetMapping("")
    public Page<PostResDTO> getAll() {
        return postService.findAll(0, 10).map(post ->
                PostResDTO.builder()
                        .id(post.getId())
                        .author(post.getAuthor())
                        .title(post.getTitle())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResWithDetailDTO> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteById(@PathVariable("id") Integer id, @RequestBody PostDelReqDTO dto) {
        // 패스워드를 body에 담기 위해 Delete 메소드가 아닌 Post 메소드 사용.
        // delete 동사가 들어간 점에서 RESTful 하지 않음.
        log.info("id={}, password={}", id, dto.getPassword());
        try{
            postService.deleteById(id, dto.getPassword());
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
