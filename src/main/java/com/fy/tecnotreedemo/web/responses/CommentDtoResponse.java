package com.fy.tecnotreedemo.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDtoResponse(@JsonProperty("id") long id,
                                 @JsonProperty("postId") long postId,
                                 @JsonProperty("name") String name,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("body") String body) {
}
