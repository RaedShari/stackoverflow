package com.raed.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.raed.stackoverflow.model.Comment;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto {
    Long id;
    String body;
    Long questionId;
    List<Comment> comments;
}
