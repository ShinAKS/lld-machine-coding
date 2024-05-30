package com.ayush.feedbackingestionservice.pojos.models.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZendeskTicket {

    @JsonProperty("ticket_id")
    String id;

    @JsonProperty("ticket")
    Ticket ticket;

}

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
class Ticket{
    String subject;
    Comment comment;
}

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
class Comment {
    String body;
}
