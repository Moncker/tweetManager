package com.example.tweetManager.tweetManager.repository;

import com.example.tweetManager.tweetManager.model.Tweet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRespository extends PagingAndSortingRepository<Tweet, Long>{

    /*
    @Query(nativeQuery=true, value="SELECT TOP 10 FROM ")
    public Collection<> first();

     */

}
