package com.example.practiceboard.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String contents;
    private String password;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reply(String author, String contents, String password) {
        this.author = author;
        this.contents = contents;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public String getPassword() {
        return password;
    }
}
