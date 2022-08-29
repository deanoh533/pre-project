/**
* AnswerService 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.answer.service;

import com.stackoverflow.answer.entity.Answer;
import com.stackoverflow.answer.repository.AnswerRepository;
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
public class AnswerService {
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    private final AnswerRepository answerRepository;

    /**
    * createAnswer 구현
     * exception 수정 필요.
    **/
    public Answer createAnswer(Answer answer) throws Exception {
        verifyExistsAnswerId(answer.getAnswerId());
        Answer savedAnswer = answerRepository.save(answer);
        return savedAnswer;
    }

    /**
    * updateAnswer 구현
     *  transactional
    **/
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContext())
                .ifPresent(context -> findAnswer.setContext(context));

        return answerRepository.save(findAnswer);
    }


    /**
    * findAnswer 구현
    **/
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);}

    /**
    * findAnswers 구현
     *  transactional
    **/
    @Transactional(readOnly = true)
    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }

    /**
    * deleteAnswer 구현
    **/
    public void deleteAnswer(long answerId) {
        Answer findAnswer = findAnswer(answerId);
        answerRepository.delete(findAnswer);
    }

    /**
    * findVerifiedAnswers 구현
     * transactional
    **/
    @Transactional(readOnly = true)
    private Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(()-> new RuntimeException()); // business예외처리 적용 필요
        return findAnswer;
    }

    /**
    * verifyExistsAnswerId 구현 - complete
     * - 기준이 id일지 고민 필요
     * error exception 추가 필요
    **/
    private void verifyExistsAnswerId(long answerId) throws Exception {
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isPresent())
            throw new Exception();
    }
}

