/**
* QuestionRepository 구현
*
* @author dean
* @version 1.0.0
* 작성일 2022/08/29
**/
package com.stackoverflow.question.repository;

import com.stackoverflow.question.entitiy.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  QuestionRepository extends JpaRepository<Question, Long> {

}
