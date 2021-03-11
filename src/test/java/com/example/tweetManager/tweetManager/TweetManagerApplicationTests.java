package com.example.tweetManager.tweetManager;

import com.example.tweetManager.tweetManager.model.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TweetManagerApplicationTests {

	@Test
	void contextLoads() {
	}


	void basic(){
		Tweet tweetFake = new Tweet();
		List<Tweet> tweets = new ArrayList<>();
		tweets.add(tweetFake);
		// Init tweet values
		tweetFake.setId(1L); tweetFake.setText("Hay mucha sombra en el centro de Vigo");
		tweetFake.setCity("Vigo"); tweetFake.setUserName("Duardo Boreira");
		tweetFake.setValidation(false);

	}


}
