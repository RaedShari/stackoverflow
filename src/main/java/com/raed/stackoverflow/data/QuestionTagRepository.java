package com.raed.stackoverflow.data;

import com.raed.stackoverflow.model.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionTagRepository extends JpaRepository<QuestionTag,Long> {
    @Query(value = "SELECT qt.tag_id FROM question_tag qt where qt.question_id = :questionId", nativeQuery = true)
    List<Long> findTagIdsByQuestionId(Long questionId);

    @Query(value = "SELECT qt.question_id FROM question_tag qt where qt.tag_id = :tagId", nativeQuery = true)
    List<Long> findQuestionIdsByTagId(Long tagId);
}
