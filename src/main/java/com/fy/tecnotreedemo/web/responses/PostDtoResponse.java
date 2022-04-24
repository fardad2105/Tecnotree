package com.fy.tecnotreedemo.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostDtoResponse(@JsonProperty("id") long id,
                              @JsonProperty("userId") long userId,
                              @JsonProperty("title") String title,
                              @JsonProperty("body") String body
) {
}
