/**
 * CommentDto 작성
 *
 * @author dean
 * @version 1.0.0
 * 작성일 2022/08/30
 **/
package com.stackoverflow.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;


public class CommentDto {
    @Getter
    public static class post{

        private long commentId;

        @Nullable
        private String context;
        @Nullable
        private long memberId;
    }

    @Getter
    public static class patch{

        private long commentId;

        @Nullable
        private String context;
        @Nullable
        private long memberId;


        public void setCommentId(long commentId) {
            this.commentId = commentId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class response{

        private long commentId;

        @Nullable
        private String context;
        @Nullable
        private long memberId;
    }



}
