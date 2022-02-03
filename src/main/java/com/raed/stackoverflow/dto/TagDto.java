package com.raed.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDto {
    Long id;
    String name;
}
