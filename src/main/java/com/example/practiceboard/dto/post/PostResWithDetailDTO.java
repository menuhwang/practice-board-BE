package com.example.practiceboard.dto.post;

import com.example.practiceboard.domain.Reply;
import lombok.Builder;

import java.util.List;

@Builder
public class PostResWithDetailDTO {
    private Integer id;
    private String author;
    private String title;
    private String contents;
    private List<Reply> replies;
}
