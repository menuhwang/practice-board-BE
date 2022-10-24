package com.example.practiceboard.service;

import com.example.practiceboard.domain.Post;
import com.example.practiceboard.dto.post.PostReqDTO;
import com.example.practiceboard.dto.post.PostResWithDetailDTO;
import org.springframework.data.domain.Page;

public interface PostService {
    Post create(PostReqDTO dto);
    Page<Post> findAll(int page, int size);
    PostResWithDetailDTO findById(Integer id);
    void deleteById(Integer id, String password);
}
