package com.ayush.feedbackingestionservice.service.handlers;

import com.ayush.feedbackingestionservice.pojos.models.Post;
import com.ayush.feedbackingestionservice.requests.PostRequest;
import com.ayush.feedbackingestionservice.service.IPostHandler;
import org.springframework.stereotype.Service;

@Service
public class AppReviewHandler implements IPostHandler {
    @Override
    public Post handlePost(PostRequest postRequest) {
        return null;
    }
}
