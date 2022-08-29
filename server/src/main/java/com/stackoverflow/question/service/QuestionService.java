/**
* QuestionService 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.question.service;

import com.stackoverflow.question.entity.Question;
import com.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    private final QuestionRepository questionRepository;

    /**
    * createQuestion 구현
     * exception 수정 필요.
    **/
    public Question createQuestion(Question question) throws Exception {
        verifyExistsQuestionId(question.getQuestionId());
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

    /**
    * updateQuestion 구현
     *  transactional
    **/
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        //Question findQuestion = new Question(1L);
        Optional.ofNullable(question.getSubject())
                .ifPresent(subject -> findQuestion.setSubject(subject));
        Optional.ofNullable(question.getContext())
                .ifPresent(context -> findQuestion.setSubject(context));

        return questionRepository.save(findQuestion);
    }


    /**
    * findQuestion 구현
    **/
    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);}

    /**
    * findQuestions 구현
     *  transactional
    **/
    @Transactional(readOnly = true)
    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }

    /**
    * deleteQuestion 구현
    **/
    public void deleteQuestion(long questionId) {
        Question findQuestion = findQuestion(questionId);
        questionRepository.delete(findQuestion);
    }

    /**
    * findVerifiedQuestions 구현
     * transactional
    **/
    @Transactional(readOnly = true)
    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(questionId);

        Question findQuestion = optionalQuestion.orElseThrow(()-> new RuntimeException()); // business예외처리 적용 필요
        return findQuestion;
    }

    /**
    * verifyExistsQuestionId 구현
     * error exception 추가 필요
    **/
    private void verifyExistsQuestionId(long questionId) throws Exception {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent())
            throw new Exception();
    }
}

