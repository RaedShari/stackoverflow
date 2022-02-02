package com.raed.stackoverflow.mapper;

import com.raed.stackoverflow.dto.AnswerDto;
import com.raed.stackoverflow.dto.CommentDto;
import com.raed.stackoverflow.dto.QuestionDto;
import com.raed.stackoverflow.dto.TagDto;
import com.raed.stackoverflow.model.Answer;
import com.raed.stackoverflow.model.Comment;
import com.raed.stackoverflow.model.Question;
import com.raed.stackoverflow.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StackoverflowMapper {
//    @Mapping(source = "title", target = "title")
    QuestionDto questionToDto(Question question);
    Question dtoToQuestion(QuestionDto questionDto);
    List<QuestionDto> questionsToDtos(List<Question> questions);

    AnswerDto answerToDto(Answer answer);
    Answer dtoToAnswer(AnswerDto answerDto);
    List<AnswerDto> answersToDtos(List<Answer> questionAnswers);

    Comment dtoToComment(CommentDto commentDto);
    CommentDto commentToDto(Comment comment);
    List<CommentDto> commentToDtos(List<Comment> questionComments);

}
