package com.example.tweetManager.tweetManager.service;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {


    @Autowired
    private TweetRespository tweetRepository;


    public TweetService(){

    }



    public List<Tweet> getAllTweets() throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


        StatusListener statusListener = new StatusListener() {
            Paging paging = new Paging(1, 100);

            @Override
            public void addStatusEvent(Status status) {
                List<String> allTweets = new ArrayList<>() {
                };
                try {
                    allTweets = twitter.getUserTimeline("google", paging).stream()
                            .map(item -> item.getText())
                            .collect(Collectors.toList());
                    ;
                    System.out.print("baia " + allTweets);

                } catch (TwitterException e) {
                    e.printStackTrace();
                }

            }
        };



        List<Tweet> tweets = new ArrayList<>();

        // for pageable
        tweetRepository.findAll();

        for(Tweet tweet : tweets)
            tweets.add(tweet);

        return tweets;
    }




}
