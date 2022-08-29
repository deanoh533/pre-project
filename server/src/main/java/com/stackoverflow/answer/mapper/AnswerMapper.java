/**
* AnswerMapper 작성
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.answer.mapper;

import com.stackoverflow.answer.dto.AnswerDto;
import com.stackoverflow.answer.entitiy.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface AnswerMapper {

    Answer answerPostToAnswer(AnswerDto.post requestBody);

    Answer answerPatchToAnswer(AnswerDto.patch requestBody);

    AnswerDto.response answerToAnswerResponse(Answer answer);

    List<AnswerDto.response> answerToAnswerResponse(List<Answer> answers);


}
