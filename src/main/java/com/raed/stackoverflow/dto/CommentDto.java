package com.raed.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.raed.stackoverflow.util.Utilities;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {

    Long id;
    Long parentId;
    Utilities.ParentType parentType;
    String body;
}
