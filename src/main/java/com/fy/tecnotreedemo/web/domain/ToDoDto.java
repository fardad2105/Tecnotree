package com.fy.tecnotreedemo.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ToDoDto(@JsonProperty("userId") long userId,
                      @JsonProperty("title") String title,
                      @JsonProperty("completed") Boolean completed) {

}
