package com.ayush.feedbackingestionservice.dao;

import com.ayush.feedbackingestionservice.pojos.models.Conversation;
import com.ayush.feedbackingestionservice.pojos.models.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository {

    Post savePost(Post post);

    Post getPostById(String postId);

    List<Post> findPostsByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    List<Post> findPostsByConversationId(String conversationId);
}
