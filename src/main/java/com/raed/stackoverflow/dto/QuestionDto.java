package com.raed.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {
    Long id;
    String title;
    String body;
    List<String> tags;
}
