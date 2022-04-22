package com.fy.tecnotreedemo.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostDto(@JsonProperty("userID") Long userId,
                      @JsonProperty("title") String title,
                      @JsonProperty("body") String body){
}
