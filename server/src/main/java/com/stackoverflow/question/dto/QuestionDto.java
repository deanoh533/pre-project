/**
* QuestionDto 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.question.dto;

import com.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;


public class QuestionDto {
    @Getter
    public static class post{

        private long questionId;

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

        private long questionId;

        private String subject;

        private String context;
        @Nullable
        private long viewCount;
        @Nullable
        private long adoptedId;
        @Nullable
        private long memberId;


        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class response{

        private long questionId;

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
