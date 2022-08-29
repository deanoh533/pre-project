/**
* CommentRepository 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/30
**/package com.stackoverflow.comment.repository;

import com.stackoverflow.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
