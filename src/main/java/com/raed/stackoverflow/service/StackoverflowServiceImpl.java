package com.raed.stackoverflow.service;

import com.raed.stackoverflow.data.*;
import com.raed.stackoverflow.model.*;
import com.raed.stackoverflow.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StackoverflowServiceImpl implements StackoverflowService {


    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    QuestionTagRepository questionTagRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(Question question, List<String> tagNames) {
        Question createdQuestion = questionRepository.save(question);
        // 1. Save the question.
        // 2. check each tag, if not exist?
        // 3. create it
        // 4. creat the relation
        List<Tag> tags = new ArrayList<>();
        tagNames.forEach(name -> {
            Tag tag;
            if(tagRepository.findByName(name).isEmpty()) {
                Tag newTag = new Tag();
                newTag.setName(name);
                tag = tagRepository.save(newTag);
            } else {
                tag = tagRepository.findByName(name).get();
            }
            QuestionTag questionTag = new QuestionTag();
            questionTag.setQuestionId(createdQuestion.getId());
            questionTag.setTagId(tag.getId());
            questionTagRepository.save(questionTag);

        });
        return createdQuestion;
    }

    @Override
    public List<Answer> getQuestionAnswers(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Comment> getComments(Long parentId, Utilities.ParentType parentType) {
        return commentRepository.findAllByParentIdAndParentType(parentId, parentType);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Tag> getTag(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    public List<String> getQuestionTags(Long questionId) {
        List<String> tags = new ArrayList<>();
        List<Long> tagIds = questionTagRepository.findTagIdsByQuestionId(questionId);
        tagIds.forEach(id -> {
            Optional<Tag> tag = getTag(id);
            if(tag.isPresent())
                tags.add(tag.get().getName());
        });
        return tags;
    }

    @Override
    public List<Question> getQuestionsByTag(String tagName) {
        List<Question> questions = new ArrayList<>();
        Optional<Tag> tag = tagRepository.findByName(tagName);
        List<Long> questionIds;
        if(tag.isPresent()) {
            questionIds = questionTagRepository.findQuestionIdsByTagId(tag.get().getId());
            questionIds.forEach(id -> {
                Optional<Question> question = getQuestion(id);
                if(question.isPresent())
                    questions.add(question.get());
            });
        }
        return questions;
    }
}
