package com.stackoverflow.answer.mapper;

import com.stackoverflow.answer.dto.AnswerDto;
import com.stackoverflow.answer.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-30T00:12:39+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerDto.post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( requestBody.getAnswerId() );
        answer.setContext( requestBody.getContext() );
        answer.setMemberId( requestBody.getMemberId() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerDto.patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( requestBody.getAnswerId() );
        answer.setContext( requestBody.getContext() );
        answer.setMemberId( requestBody.getMemberId() );

        return answer;
    }

    @Override
    public AnswerDto.response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        long answerId = 0L;
        String context = null;
        long memberId = 0L;

        answerId = answer.getAnswerId();
        context = answer.getContext();
        memberId = answer.getMemberId();

        AnswerDto.response response = new AnswerDto.response( answerId, context, memberId );

        return response;
    }

    @Override
    public List<AnswerDto.response> answerToAnswerResponse(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.response> list = new ArrayList<AnswerDto.response>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponse( answer ) );
        }

        return list;
    }
}
