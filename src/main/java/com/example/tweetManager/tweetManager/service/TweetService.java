package com.example.tweetManager.tweetManager.service;

import ch.qos.logback.core.status.StatusListener;
import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
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

        String query = "barcelona";
        QueryResult search = twitter.search(new Query(query));
        List<Status> tweets = search.getTweets();


        return new ArrayList<Tweet>();


        /*
        StatusListener statusListener = new StatusListener() {
            Paging paging = new Paging(1, 100);

            @Override
            public void addStatusEvent(Status status) {
                List<String> allTweets = new ArrayList<>() {
                };
                try {
                    allTweets = twitter.getUserTimeline("rye761", paging).stream()
                            .map(item -> item.getText())
                            .collect(Collectors.toList());
                    ;
                    System.out.print("baia " + allTweets);

                } catch (TwitterException e) {
                    e.printStackTrace();
                }

            }
        };




        // for pageable
        tweetRepository.findAll();

        for(Tweet tweet : tweetList)
            tweetList.add(tweet);

        return tweetList;
*/    }






}
