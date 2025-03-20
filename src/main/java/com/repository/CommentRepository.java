package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByBlogId(Long blogId);
	@Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.blog.id = :blogId")
    void deleteByBlogId(Long blogId);
}