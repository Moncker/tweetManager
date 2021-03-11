package com.example.tweetManager.tweetManager.service;

import com.example.tweetManager.tweetManager.TweetManagerApplication;
import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;

import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class TweetSv {
    @Autowired
    TweetRespository tweetRespository;

    static List<Tweet> tweetValidate = new LinkedList<>();

    @Async
    public CompletableFuture<List<Tweet>> allTweets(@Nullable Integer page) throws Exception {
        Page<Tweet> pageTweets;

        if (page != null)
            pageTweets = tweetRespository.findAll(PageRequest.of(page, 10));
        else
            pageTweets = tweetRespository.findAll(PageRequest.of(0, 10));

        return CompletableFuture.completedFuture(pageTweets.toList());
    }

    @Async
    public CompletableFuture<List<Tweet>> allValidates(@Nullable Integer page) {
        Page<Tweet> pageTweets;

        // TODO - By search
        return CompletableFuture.completedFuture(tweetValidate);
    }


    @Async
    public CompletableFuture<Boolean> markAsValidate(Long idTweet) {
        Optional<Tweet> tweet = tweetRespository.findById(idTweet);
        if (tweet.isEmpty())
            return CompletableFuture.completedFuture(false);
        else{
            tweet.get().setValidation(true);
            tweetValidate.add(tweet.get());
            tweetRespository.save(tweet.get());
            return CompletableFuture.completedFuture(true);
        }
    }



    @Async
    public CompletableFuture<List<Tweet>> allValidatesByUser(String user) {
        List<Tweet> tweets = new LinkedList<>();
        for(Tweet t : tweetValidate)
            if (t.getUserName().equals(user))
                tweets.add(t);
        return CompletableFuture.completedFuture(tweets);
    }

    // TODO - Hacer por consulta SQL
    // TODO - Mover variables fichero config
    public CompletableFuture<List<String>> topHastags(Integer cant) {
         LinkedList<String> hastagListStamp = new LinkedList<>(TweetManagerApplication.HASTAG_LIST);
         Map<String, Integer> ranking = new ManagedMap<>();
         int occurrences;

         if(cant == null)
             cant = 10;

        for (String h : hastagListStamp) {
            occurrences = Collections.frequency(hastagListStamp, h);
            ranking.put(h, occurrences);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(ranking.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Integer iCant = new Integer(0);

        List<String> result = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : list) {
            if (cant.compareTo(iCant) == 0)
                break;
            result.add(entry.getKey());
            iCant ++;
        }

        return CompletableFuture.completedFuture(result);

    }
}
