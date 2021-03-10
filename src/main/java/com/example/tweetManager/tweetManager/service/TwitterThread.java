package com.example.tweetManager.tweetManager.service;


import com.example.tweetManager.tweetManager.repository.TweetRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TwitterThread extends Thread{

    final ArrayList<String> ACCEPTED_LAN = new ArrayList<String>(
            Arrays.asList("es", "fr", "it"));


    @Autowired
    TweetRespository tweetRespository;

    @Override
    public void run(){
        startConnection();


    }

    public void startConnection() {

        final StatusListener statusListener;
        log.info("STARTING CONNECTION...");

        //Logger log =
        //log.info("Establishing connection.");

        FilterQuery query = simpleFilter();
        TwitterStream twitterStream = streamFeed().filter(query);

       // twitterStream.onStatus();

        //twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

    }

    public TwitterStream streamFeed() {
        TwitterStream twitterStream;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);

        twitterStream = (new TwitterStreamFactory(cb.build())).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
            }
            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }
            @Override
            public void onStallWarning(StallWarning warning) {
            }
            @Override
            public void onStatus(Status status) {
                //status.getGeoLocation();

                if (ACCEPTED_LAN.contains( status.getLang())) {
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }
            }
            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }
        };

        twitterStream = new TwitterStreamFactory().getInstance();

        twitterStream.addListener(listener);

        return twitterStream.sample();
    }

    public FilterQuery simpleFilter(){
        String followParam = "11348282,20536157,15670515,1719374,58561993";
        String trackParam = "Amazon,Google,Apple";
        String locationParam = "-122.75,36.8,-121.75,37.8,-74,40,-73,41";
        long[] follow = new long[followParam.split(",").length];
        for (int i = 0; i < followParam.split(",").length; i++) {
            follow[i] = Long.parseLong(followParam.split(",")[i]);
        }
        String[] boundary = locationParam.split(",");
        double[][] locations = new double[boundary.length / 2][2];
        int k = 0;
        for (int i = 0; i < boundary.length / 2; i++) {
            for (int j = 0; j < 2; j++) {
                locations[i][j] = Double.parseDouble(boundary[k++]);
            }
        }
        FilterQuery query = new FilterQuery();
        query.follow(follow);
        query.track(trackParam.split(","));
        query.locations(locations);
        return query;
    }


}
