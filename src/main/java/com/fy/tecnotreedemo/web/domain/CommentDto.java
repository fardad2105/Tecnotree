package com.fy.tecnotreedemo.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDto(@JsonProperty ("name") String name,
                         @JsonProperty ("email") String email,
                         @JsonProperty ("body") String body) {
}
