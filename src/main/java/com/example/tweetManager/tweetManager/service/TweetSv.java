package com.example.tweetManager.tweetManager.service;

import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TweetSv {
    @Autowired
    TweetRespository tweetRespository;

    @Async
    public CompletableFuture<List<Tweet>> allTweets() throws Exception {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();

        for (Tweet t : tweetRespository.findAll())
            tweets.add(t);

        return CompletableFuture.completedFuture(tweets);
    }



}
