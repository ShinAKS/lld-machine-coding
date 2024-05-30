package com.ayush.feedbackingestionservice.service;

import com.ayush.feedbackingestionservice.pojos.enums.PostType;
import com.ayush.feedbackingestionservice.service.handlers.AppReviewHandler;
import com.ayush.feedbackingestionservice.service.handlers.ChatHandler;
import com.ayush.feedbackingestionservice.service.handlers.TweetHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostHandlerFactory {

    Map<PostType,IPostHandler> postHandlerMap = new HashMap<>();

    @Autowired
    public AppReviewHandler appReviewHandler;

    @Autowired
    public ChatHandler chatHandler;

    @Autowired
    public TweetHandler tweetHandler;

    @PostConstruct
    public void setup(){
        postHandlerMap.put(PostType.CHAT, chatHandler);
        postHandlerMap.put(PostType.REVIEW, appReviewHandler);
        postHandlerMap.put(PostType.TWEET, tweetHandler);
    }



}
