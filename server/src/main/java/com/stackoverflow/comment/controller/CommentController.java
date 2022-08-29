/** 
* CommentController 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/30
**/
package com.stackoverflow.comment.controller;

import com.stackoverflow.comment.dto.CommentDto;
import com.stackoverflow.comment.entity.Comment;
import com.stackoverflow.comment.mapper.CommentMapper;
import com.stackoverflow.comment.service.CommentService;
import com.stackoverflow.dto.MultiResponseDto;
import com.stackoverflow.dto.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/comment")
//@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }


    /**
     * postMember 구현
     * singleResponseDto 구현 기능 추가 예정. - complete
     * @valid 관련 내용 보충 필요
     **/
    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.post requestBody) throws Exception {
        Comment comment = mapper.commentPostToComment(requestBody);
        Comment createdComment = commentService.createComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponse(createdComment)), HttpStatus.CREATED
        );
    }

    /**
     * patchComment 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
     * patch 시에 기준 인덱스필드 정해야함.
     **/
    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                      @Valid @RequestBody CommentDto.patch requestBody){
        requestBody.setCommentId(commentId);
        Comment comment = mapper.commentPatchToComment(requestBody);
        Comment response = commentService.updateComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponse(response)), HttpStatus.CREATED);

    }


    /**
     * getComment 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
     **/
    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") @Positive long commentId){
        Comment comment = commentService.findComment(commentId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponse(comment)) , HttpStatus.CREATED);
    }


    /**
     * getComments 구현
     * multiResponseDto 구현 기능 추가 예정.
     * page, size  관련 내용 추가 필요.
     **/
    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Comment> pageComment = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComment.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentToCommentResponse(comments), pageComment),
                HttpStatus.CREATED);
    }

    /**
     * deleteComment 구현
     **/
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(
            @PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

