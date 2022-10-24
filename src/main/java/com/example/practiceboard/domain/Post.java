package com.example.practiceboard.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String title;
    private String contents;
    private String password;
    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    public Post(String author, String title, String contents, String password) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getPassword() {
        return password;
    }

    public List<Reply> getReplies() {
        return replies;
    }
}
