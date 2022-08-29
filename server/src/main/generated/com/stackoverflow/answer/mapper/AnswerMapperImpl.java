package com.stackoverflow.answer.mapper;

import com.stackoverflow.answer.dto.AnswerDto;
import com.stackoverflow.answer.entitiy.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-29T18:45:56+0900",
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
        answer.setSubject( requestBody.getSubject() );
        answer.setContext( requestBody.getContext() );
        answer.setViewCount( requestBody.getViewCount() );
        answer.setAdoptedId( requestBody.getAdoptedId() );
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
        answer.setSubject( requestBody.getSubject() );
        answer.setContext( requestBody.getContext() );
        answer.setViewCount( requestBody.getViewCount() );
        answer.setAdoptedId( requestBody.getAdoptedId() );
        answer.setMemberId( requestBody.getMemberId() );

        return answer;
    }

    @Override
    public AnswerDto.response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        long answerId = 0L;
        String subject = null;
        String context = null;
        long viewCount = 0L;
        long adoptedId = 0L;
        long memberId = 0L;

        answerId = answer.getAnswerId();
        subject = answer.getSubject();
        context = answer.getContext();
        viewCount = answer.getViewCount();
        adoptedId = answer.getAdoptedId();
        memberId = answer.getMemberId();

        AnswerDto.response response = new AnswerDto.response( answerId, subject, context, viewCount, adoptedId, memberId );

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
