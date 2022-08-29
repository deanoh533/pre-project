/**
 * CommentService 구현
 *
 * @author dean
 * @version 1.0.0
 * 작성일 2022/08/30
 **/
package com.stackoverflow.comment.service;

import com.stackoverflow.comment.entity.Comment;
import com.stackoverflow.comment.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Service
public class CommentService {
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    private final CommentRepository commentRepository;

    /**
     * createComment 구현
     * exception 수정 필요.
     **/
    public Comment createComment(Comment comment) throws Exception {
        verifyExistsCommentId(comment.getCommentId());
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    /**
     * updateComment 구현
     *  transactional
     **/
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getContext())
                .ifPresent(context -> findComment.setContext(context));

        return commentRepository.save(findComment);
    }


    /**
     * findComment 구현
     **/
    public Comment findComment(long commentId) {
        return findVerifiedComment(commentId);}

    /**
     * findComments 구현
     *  transactional
     **/
    @Transactional(readOnly = true)
    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size,
                Sort.by("commentId").descending()));
    }

    /**
     * deleteComment 구현
     **/
    public void deleteComment(long commentId) {
        Comment findComment = findComment(commentId);
        commentRepository.delete(findComment);
    }

    /**
     * findVerifiedComments 구현
     * transactional
     **/
    @Transactional(readOnly = true)
    private Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);

        Comment findComment = optionalComment.orElseThrow(()-> new RuntimeException()); // business예외처리 적용 필요
        return findComment;
    }

    /**
     * verifyExistsCommentId 구현 - complete
     * - 기준이 id일지 고민 필요
     * error exception 추가 필요
     **/
    private void verifyExistsCommentId(long commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent())
            throw new Exception();
    }
}

