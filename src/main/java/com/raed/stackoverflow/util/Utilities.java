package com.raed.stackoverflow.util;

import com.raed.stackoverflow.exception.NoContentException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Utilities {

    public void handelEmptyList(List list){
        if (list != null && list.isEmpty()) {
            throw new NoContentException();
        }
    }

    public enum ParentType {
        Question,
        Answer,
        Comment
    }

}
