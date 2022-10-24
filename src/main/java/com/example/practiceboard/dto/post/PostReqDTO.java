package com.example.practiceboard.dto.post;

import com.example.practiceboard.domain.Post;
import lombok.Builder;

@Builder
public class PostReqDTO {
    private String author;
    private String title;
    private String contents;
    private String password;

    public Post toEntity() {
        return new Post(
                author,
                title,
                contents,
                password
        );
    }
}
