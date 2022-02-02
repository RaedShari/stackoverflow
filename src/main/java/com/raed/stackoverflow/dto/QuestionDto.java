package com.raed.stackoverflow.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.raed.stackoverflow.model.Answer;
import com.raed.stackoverflow.model.Comment;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {
    Long id;
    String title;
    String body;
    List<Answer> answers;
    List<Comment> comments;
    List<String> tags;
}
