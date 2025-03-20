package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(nullable = false, length = 200)
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(Long id, Blog blog, String comment) {
        this.id = id;
        this.blog = blog;
        this.comment = comment;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", blog=" + blog + ", comment=" + comment + "]";
    }
}
