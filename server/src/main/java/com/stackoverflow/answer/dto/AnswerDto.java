/**
* AnswerDto 작성
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;


public class AnswerDto {
    @Getter
    public static class post{

        private long answerId;

        private String subject;

        private String context;
        @Nullable
        private long viewCount;
        @Nullable
        private long adoptedId;
        @Nullable
        private long memberId;
    }

    @Getter
    public static class patch{

        private long answerId;

        private String subject;

        private String context;
        @Nullable
        private long viewCount;
        @Nullable
        private long adoptedId;
        @Nullable
        private long memberId;


        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class response{

        private long answerId;

        private String subject;

        private String context;
        @Nullable
        private long viewCount;

        @Nullable
        private long adoptedId;
        @Nullable
        private long memberId;
    }



}
