package com.example.tweetManager.tweetManager.controller;

import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TweetController {

    @Autowired
    TweetService tweetService;


    @RequestMapping(value = "/tweets",
                    method = RequestMethod.GET,
                    produces = {"application/json" , "application/xml"})
    public @ResponseBody
    ResponseEntity<List<Tweet>> getTweets (HttpServletRequest request, HttpServletResponse response) throws TwitterException {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Tweet tweetFake = new Tweet();
        tweets.add(tweetFake);
        // Init tweet values
        tweetFake.setId(1); tweetFake.setText("Hay mucha sombra en el centro de Vigo");
        tweetFake.setLocalisation("Vigo"); tweetFake.setUserName("Duardo Boreira");
        tweetFake.setValidation(false);


        tweets.addAll(tweetService.getAllTweets());


        return ResponseEntity.status(200)
                .body(tweets);
    }



    @RequestMapping(value = "/validate/{idTweet}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<List<Tweet>> markTweetAsValidate (@PathVariable("idTweet") Integer idTweet,
                                           HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        return null;
    }

    @RequestMapping(value = "/validates",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<List<Tweet>> getValidateTweets (@PathVariable("idTweet") Integer idTweet,
                                           HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        return null;
    }


    @RequestMapping(value = "/validates/{user}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<List<Tweet>> getValidateTweets (@PathVariable("user") String user,
                                                   HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        return null;
    }







}
