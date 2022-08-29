package com.stackoverflow.question.mapper;

import com.stackoverflow.question.dto.QuestionDto;
import com.stackoverflow.question.entity.Question;
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
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostToQuestion(QuestionDto.post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( requestBody.getQuestionId() );
        question.setSubject( requestBody.getSubject() );
        question.setContext( requestBody.getContext() );
        question.setViewCount( requestBody.getViewCount() );
        question.setAdoptedId( requestBody.getAdoptedId() );
        question.setMemberId( requestBody.getMemberId() );

        return question;
    }

    @Override
    public Question questionPatchToQuestion(QuestionDto.patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( requestBody.getQuestionId() );
        question.setSubject( requestBody.getSubject() );
        question.setContext( requestBody.getContext() );
        question.setViewCount( requestBody.getViewCount() );
        question.setAdoptedId( requestBody.getAdoptedId() );
        question.setMemberId( requestBody.getMemberId() );

        return question;
    }

    @Override
    public QuestionDto.response questionToQuestionResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        long questionId = 0L;
        String subject = null;
        String context = null;
        long viewCount = 0L;
        long adoptedId = 0L;
        long memberId = 0L;

        questionId = question.getQuestionId();
        subject = question.getSubject();
        context = question.getContext();
        viewCount = question.getViewCount();
        adoptedId = question.getAdoptedId();
        memberId = question.getMemberId();

        QuestionDto.response response = new QuestionDto.response( questionId, subject, context, viewCount, adoptedId, memberId );

        return response;
    }

    @Override
    public List<QuestionDto.response> questionToQuestionResponse(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.response> list = new ArrayList<QuestionDto.response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponse( question ) );
        }

        return list;
    }
}
