package com.stackoverflow.answer.repository;

import com.stackoverflow.answer.entitiy.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
