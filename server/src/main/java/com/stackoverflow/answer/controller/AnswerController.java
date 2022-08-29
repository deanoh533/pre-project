/**
* AnswerController 작성
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/

package com.stackoverflow.answer.controller;

import com.stackoverflow.dto.MultiResponseDto;
import com.stackoverflow.dto.SingleResponseDto;
import com.stackoverflow.answer.dto.AnswerDto;
import com.stackoverflow.answer.entitiy.Answer;
import com.stackoverflow.answer.mapper.AnswerMapper;
import com.stackoverflow.answer.service.AnswerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/answer")
//@Validated
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }


    /**
     * postMember 구현
     * singleResponseDto 구현 기능 추가 예정. - complete
     * @valid 관련 내용 보충 필요
    **/
    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.post requestBody) throws Exception {
        Answer answer = mapper.answerPostToAnswer(requestBody);
        Answer createdAnswer = answerService.createAnswer(answer);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(createdAnswer)), HttpStatus.CREATED
        );
    }

    /**
     * patchAnswer 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
     * patch 시에 기준 인덱스필드 정해야함.
    **/
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
            @Valid @RequestBody AnswerDto.patch requestBody){
        requestBody.setAnswerId(answerId);
        Answer answer = mapper.answerPatchToAnswer(requestBody);
        Answer response = answerService.updateAnswer(answer);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(response)), HttpStatus.CREATED);

    }


    /**
     * gtAnswer 구현
     * singleResponseDto 구현 기능 추가 예정.  - complete
    **/
    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId){
        Answer answer = answerService.findAnswer(answerId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponse(answer)) , HttpStatus.CREATED);
    }


    /**
    * getAnswers 구현
     * multiResponseDto 구현 기능 추가 예정.
     * page, size  관련 내용 추가 필요.
    **/
    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size){
        Page<Answer> pageAnswer = answerService.findAnswers(page - 1, size);
        List<Answer> answers = pageAnswer.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answerToAnswerResponse(answers), pageAnswer),
                HttpStatus.CREATED);
    }

    /**
    * deleteAnswer 구현
    **/
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
