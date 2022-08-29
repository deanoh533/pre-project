/**
* QuestionMapper 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.question.mapper;

import com.stackoverflow.question.dto.QuestionDto;
import com.stackoverflow.question.entitiy.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface QuestionMapper {

    Question questionPostToQuestion(QuestionDto.post requestBody);

    Question questionPatchToQuestion(QuestionDto.patch requestBody);

    QuestionDto.response questionToQuestionResponse(Question question);

    List<QuestionDto.response> questionToQuestionResponse(List<Question> questions);


}
