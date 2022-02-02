package com.raed.stackoverflow.service;

import com.raed.stackoverflow.model.Answer;
import com.raed.stackoverflow.model.Comment;
import com.raed.stackoverflow.model.Question;
import com.raed.stackoverflow.model.Tag;
import com.raed.stackoverflow.util.Utilities;

import java.util.List;
import java.util.Optional;


public interface StackoverflowService {
    List<Question> getAllQuestions();
    Optional<Question> getQuestion(Long id);

    Question createQuestion(Question question, List<String> tags);

    List<Answer> getQuestionAnswers(Long questionId);

    Answer createAnswer(Answer answer);

    List<Comment> getComments(Long answerId, Utilities.ParentType parentType);

    Comment createComment(Comment comment);

    Optional<Tag> getTag(Long tagId);
    List<String> getQuestionTags(Long questionId);

    List<Question> getQuestionsByTag(String tag);
}
