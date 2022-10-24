package com.example.practiceboard.service;

import com.example.practiceboard.domain.Post;
import com.example.practiceboard.dto.post.PostReqDTO;
import com.example.practiceboard.dto.post.PostResWithDetailDTO;
import com.example.practiceboard.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(PostReqDTO dto) {
        return postRepository.save(dto.toEntity());
    }

    public PostResWithDetailDTO findById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을  찾을 수 없습니다."));
        return PostResWithDetailDTO.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .contents(post.getContents())
                .replies(post.getReplies())
                .build();
    }

    public Page<Post> findAll(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))));
    }

    public void deleteById(Integer id, String password) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        if(password.equals(post.getPassword())) {
            postRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("패스워드가 일치하지 않습니다.");
    }
}
