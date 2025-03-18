package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BlogDto {
	private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 3, max = 200)
    private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BlogDto(Long id, @NotBlank @Size(min = 3, max = 100) String title,
			@NotBlank @Size(min = 3, max = 200) String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public BlogDto() {
		super();
	}

	@Override
	public String toString() {
		return "BlogDto [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
}
