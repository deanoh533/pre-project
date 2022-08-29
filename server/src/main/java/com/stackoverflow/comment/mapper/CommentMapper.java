/**
 * CommentMapper 작성
 *
 * @author dean
 * @version 1.0.0
 * 작성일 2022/08/30
 **/
package com.stackoverflow.comment.mapper;


import com.stackoverflow.comment.dto.CommentDto;
import com.stackoverflow.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface CommentMapper {

    Comment commentPostToComment(CommentDto.post requestBody);
    Comment commentPatchToComment(CommentDto.patch requestBody);
    CommentDto.response commentToCommentResponse(Comment comment);
    List<CommentDto.response> commentToCommentResponse(List<Comment> comments);


}
