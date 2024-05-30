package com.ayush.feedbackingestionservice.pojos.models.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

    @JsonProperty("tweet_id")
    private String id;

    private String message;
    private int likes;
    private int views;


}
