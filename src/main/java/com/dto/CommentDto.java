package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentDto {
	 private Long id;

	    @NotBlank
	    @Size(min = 3, max = 100)
	    private String title;

	    @NotBlank
	    @Size(min = 3, max = 200)
	    
	    private String comment;

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

		public String getComment() {
			return comment;
		}

		public void setComment(String content) {
			this.comment = content;
		}

		public CommentDto(Long id,@NotBlank @Size(min = 3, max = 100) String title,@NotBlank @Size(min = 3, max = 200) String content) {
			super();
			this.id = id;
			this.title = title;
			this.comment = content;
		}

		public CommentDto() {
			super();
		}

		@Override
		public String toString() {
			return "CommentDto [id=" + id + ", title=" + title + ", content=" + comment + "]";
		}
}
