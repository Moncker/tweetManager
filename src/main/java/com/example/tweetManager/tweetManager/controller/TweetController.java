package com.example.tweetManager.tweetManager.controller;

import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;
import com.example.tweetManager.tweetManager.service.TweetSv;
import com.example.tweetManager.tweetManager.validators.TweetValidator;
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

// TODO - Paginacion
    @RequestMapping(value = "/tweets/{page}",
                    method = RequestMethod.GET,
                    produces = {"application/json" , "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getTweetsPage ( @PathVariable(value="page", required = false) Integer page,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
            return tweetSv.allTweets(page).<ResponseEntity>thenApply(ResponseEntity::ok)
                    .exceptionally(handleGetProductFailure);
    }

    @RequestMapping(value = "/tweets",
                    method = RequestMethod.GET,
                    produces = {"application/json" , "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getTweets (
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
            return tweetSv.allTweets(null).<ResponseEntity>thenApply(ResponseEntity::ok)
                    .exceptionally(handleGetProductFailure);
    }

    @RequestMapping(value = "/validate/{idTweet}",
            method = RequestMethod.PUT,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> markTweetAsValidate (@PathVariable("idTweet") Long idTweet,
                                           HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        return tweetSv.markAsValidate(idTweet).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }

    @RequestMapping(value = "/validates",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity>  getValidateTweets ( @PathVariable(value="page", required = false) Integer page,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response) throws TwitterException {
        return tweetSv.allValidates(page).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }

    @RequestMapping(value = "/validates/{page}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity>  getValidateTweetsPage ( @PathVariable("idTweet") Long idTweet,
                                                            HttpServletRequest request,
                                                          HttpServletResponse response) throws TwitterException {
        return tweetSv.allValidates(null).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }

    @RequestMapping(value = "/validates/{user}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<List<Tweet>> getValidateTweetsByUser (@PathVariable("user") String user,
                                                   HttpServletRequest request, HttpServletResponse response) throws TwitterException {
        tweetSv.allValidatesByUser(user);

        TweetValidator.validateUser(user);
        //validar - el user no existe: 404

        return null;
    }
    private static Function<Throwable, ResponseEntity<? extends String>> handleGetProductFailure = throwable -> {
        //LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };


    @RequestMapping(value="/tophashtags",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getTopHashtags ( HttpServletRequest request, HttpServletResponse response) throws TwitterException {
        return tweetSv.topHastags(null).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }


    @RequestMapping(value="/tophashtags/{cant}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getTopHashtagsCant (@PathVariable("cant") Integer cant,
            HttpServletRequest request, HttpServletResponse response) throws TwitterException {
        return tweetSv.topHastags(cant).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetProductFailure);
    }
}
