package com.example.tweetManager.tweetManager.controller;

import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.service.TweetSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


@RestController
public class TweetController {

    @Autowired
    TweetSv tweetSv;


    @RequestMapping(value = "/tweets",
                    method = RequestMethod.GET,
                    produces = {"application/json" , "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getTweets (HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Tweet tweetFake = new Tweet();
        tweets.add(tweetFake);
        // Init tweet values
        tweetFake.setId(1L); tweetFake.setText("Hay mucha sombra en el centro de Vigo");
        tweetFake.setCity("Vigo"); tweetFake.setUserName("Duardo Boreira");
        tweetFake.setValidation(false);

        return tweetSv.allTweets().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends String>> handleGetProductFailure = throwable -> {
        //LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };


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
    ResponseEntity<List<Tweet>> getValidateTweetsByUser (@PathVariable("user") String user,
                                                   HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        return null;
    }







}
