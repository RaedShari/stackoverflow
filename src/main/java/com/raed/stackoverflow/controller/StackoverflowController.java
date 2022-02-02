package com.raed.stackoverflow.controller;

import com.raed.stackoverflow.dto.AnswerDto;
import com.raed.stackoverflow.dto.CommentDto;
import com.raed.stackoverflow.dto.QuestionDto;
import com.raed.stackoverflow.dto.TagDto;
import com.raed.stackoverflow.exception.NotFoundException;
import com.raed.stackoverflow.mapper.StackoverflowMapper;
import com.raed.stackoverflow.service.StackoverflowService;
import com.raed.stackoverflow.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class StackoverflowController {

    @Autowired
    StackoverflowMapper stackoverflowMapper;

    @Autowired
    StackoverflowService stackoverflowService;

    @Autowired
    Utilities utilities;

    @GetMapping("/questions")
    public List<QuestionDto> getQuestions(@RequestParam(required = false) String tag) {
        List<QuestionDto> questions;

        if(tag != null){
            questions= stackoverflowMapper.questionsToDtos(stackoverflowService.getQuestionsByTag(tag));
        } else {
            questions = stackoverflowMapper.questionsToDtos(stackoverflowService.getAllQuestions());
        }
        utilities.handelEmptyList(questions);
        return questions;
    }

    @GetMapping("/questions/{questionId}")
    public QuestionDto getQuestion(@PathVariable Long questionId) {
        try {
            QuestionDto question = stackoverflowMapper.questionToDto(stackoverflowService.getQuestion(questionId).get());
            question.setTags(stackoverflowService.getQuestionTags(questionId));
            return question;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("resource.not.found",e.getMessage(),"questionId");
        }
    }

    @PostMapping("/questions")
    public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
        List<String> tags = questionDto.getTags();
        return stackoverflowMapper.questionToDto(stackoverflowService.createQuestion(stackoverflowMapper.dtoToQuestion(questionDto),tags));
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<AnswerDto> getQuestionAnswers(@PathVariable Long questionId) {
        List<AnswerDto> answers = stackoverflowMapper.answersToDtos(stackoverflowService.getQuestionAnswers(questionId));
        utilities.handelEmptyList(answers);
        return answers;
    }

    @PostMapping("/questions/{questionId}/answers")
    public AnswerDto createAnswer(@PathVariable Long questionId, @RequestBody AnswerDto answerDto) {
        answerDto.setQuestionId(questionId);
        return stackoverflowMapper.answerToDto(stackoverflowService.createAnswer(stackoverflowMapper.dtoToAnswer(answerDto)));
    }

    @GetMapping("/questions/{questionId}/comments")
    public List<CommentDto> getQuestionComments(@PathVariable Long questionId) {
        List<CommentDto> comments = stackoverflowMapper.commentToDtos(stackoverflowService.getComments(questionId, Utilities.ParentType.Question));
        utilities.handelEmptyList(comments);
        return comments;
    }

    @PostMapping("/questions/{questionId}/comments")
    public CommentDto createQuestionComment(@PathVariable Long questionId, @RequestBody CommentDto commentDto) {
        commentDto.setParentId(questionId);
        commentDto.setParentType(Utilities.ParentType.Question);
        return stackoverflowMapper.commentToDto(stackoverflowService.createComment(stackoverflowMapper.dtoToComment(commentDto)));
    }

    @GetMapping("/answers/{answerId}/comments")
    public List<CommentDto> getAnswerComments(@PathVariable Long answerId) {
        List<CommentDto> comments = stackoverflowMapper.commentToDtos(stackoverflowService.getComments(answerId, Utilities.ParentType.Answer));
        utilities.handelEmptyList(comments);
        return comments;
    }

    @PostMapping("/answers/{answersId}/comments")
    public CommentDto createAnswerComment(@PathVariable Long answersId, @RequestBody CommentDto commentDto) {
        commentDto.setParentId(answersId);
        commentDto.setParentType(Utilities.ParentType.Answer);
        return stackoverflowMapper.commentToDto(stackoverflowService.createComment(stackoverflowMapper.dtoToComment(commentDto)));
    }

}
