package com.ayush.feedbackingestionservice.requests;

import com.ayush.feedbackingestionservice.pojos.enums.PostType;
import com.ayush.feedbackingestionservice.pojos.models.RequestSourceAttribute;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
public class CreatePostRequest {

    @Nullable
    String conversationId;
    String postedBy;
    LocalDateTime createdAt;
    PostType postType;
    String content;
    RequestSourceAttribute requestSourceAttribute;

}
