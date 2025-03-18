package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Comment(Long id, String comment, Blog blog) {
		super();
		this.id = id;
		this.comment = comment;
		this.blog = blog;
	}

	public Comment() {
		super();
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", blog=" + blog + "]";
	}
}
