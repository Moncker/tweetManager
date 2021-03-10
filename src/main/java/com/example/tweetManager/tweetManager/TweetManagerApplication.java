package com.example.tweetManager.tweetManager;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import com.example.tweetManager.tweetManager.service.TwitterThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.*;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class TweetManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetManagerApplication.class, args);
		TwitterThread twitterThread = new TwitterThread();
		twitterThread.start();

	}
}

	/*
	public static void startConnection() {
		final TwitterStream twitterStream;
		final StatusListener statusListener;



		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);



		//TwitterFactory tf = new TwitterFactory(cb.build());
		//Twitter twitter = tf.getInstance();

		twitterStream = new TwitterStreamFactory(cb.build()).getInstance();}}
		/*twitterStream.addListener(new StatusListener () {
									  public void onStatus(Status status) {
										  System.out.println(status.getText()) // print tweet text to console
									  }

	}


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
	}
}
*/
/*
	public static void activationTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(OAUTH_CONSUMER_KEY);
		cb.setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET);
		cb.setOAuthAccessToken(OAUTH_ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);


		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			public void onStatus(Status status) {
				System.out.println(status.getText());

			}

			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}
		};
	}*/

