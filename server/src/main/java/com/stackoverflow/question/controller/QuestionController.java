/**
* QuestionController 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/

package com.stackoverflow.question.controller;

import com.stackoverflow.dto.MultiResponseDto;
import com.stackoverflow.dto.SingleResponseDto;
import com.stackoverflow.question.dto.QuestionDto;
import com.stackoverflow.question.entitiy.Question;
import com.stackoverflow.question.mapper.QuestionMapper;
import com.stackoverflow.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/question")
//@Validated
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }


    /**
     * postMember 구현
     * singleResponseDto 구현 기능 추가 예정. - complete
     * @valid 관련 내용 보충 필요
    **/
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.post requestBody) throws Exception {
        Question question = mapper.questionPostToQuestion(requestBody);
        Question createdQuestion = questionService.createQuestion(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponse(createdQuestion)), HttpStatus.CREATED
        );
    }

    /**
     * patchQuestion 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
     * patch 시에 기준 인덱스필드 정해야함.
    **/
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
            @Valid @RequestBody QuestionDto.patch requestBody){
        requestBody.setQuestionId(questionId);
        Question question = mapper.questionPatchToQuestion(requestBody);
        Question response = questionService.updateQuestion(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponse(response)), HttpStatus.CREATED);

    }


    /**
     * gtQuestion 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
    **/
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId){
        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponse(question)) , HttpStatus.CREATED);
    }


    /**
    * getQuestions 구현
     * multiResponseDto 구현 기능 추가 예정.
     * page, size  관련 내용 추가 필요.
    **/
    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size){
        Page<Question> pageQuestion = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestion.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionToQuestionResponse(questions), pageQuestion),
                HttpStatus.CREATED);
    }

    /**
    * deleteQuestion 구현
    **/
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(
            @PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
