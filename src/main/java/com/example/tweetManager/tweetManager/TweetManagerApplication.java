package com.example.tweetManager.tweetManager;

import com.example.tweetManager.tweetManager.model.Tweet;
import com.example.tweetManager.tweetManager.repository.TweetRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


@SpringBootApplication
public class TweetManagerApplication {
	final static ArrayList<String> ACCEPTED_LAN = new ArrayList<String>(
			Arrays.asList("es", "fr", "it"));
	final static int MIN_FOLLOWERS = 1500;
	public final static LinkedList<String> HASTAG_LIST = new LinkedList<>();

	@Autowired
	TweetRespository tweetRepository;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TweetManagerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public TwitterStream streamFeed() {
		TwitterStream twitterStream;

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);

		twitter4j.StatusListener listener = new StatusListener() {

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
				int followers = status.getUser().getFollowersCount();
				String language = status.getLang();

				if ( MIN_FOLLOWERS < followers
						&& ACCEPTED_LAN.contains(language.toLowerCase())) {
					//new tweet in persistence
					Tweet tweet = new Tweet();

					tweet.setId(status.getId());
					tweet.setText(status.getText());
					tweet.setUserName(status.getUser().getName());
					Place place = status.getPlace();
					if (place != null) {
						tweet.setCity(place.getName());
						tweet.setCountry(place.getCountry());
					}
					tweet.setValidation(false);

					HashtagEntity[] hashEnt = status.getHashtagEntities();

					for (HashtagEntity h : hashEnt)
						HASTAG_LIST.add(h.getText());

					tweetRepository.save(tweet);
					tweetRepository.findById(tweet.getId());
					System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				}
			}
			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}
		};

		twitterStream = (new TwitterStreamFactory(cb.build())).getInstance();
		//twitterStream = new TwitterStreamFactory().getInstance();

		twitterStream.addListener(listener);
		return twitterStream.sample();
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

