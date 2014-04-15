package rss.tests;

import rss.model.Feed;
import rss.model.FeedMessage;
import rss.read.RSSFeedParser;

public class ReadTest {
	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser(
				"http://www.lemonde.fr/rss/tag/ete.xml");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		System.out.println();
		System.out.println();
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);
			System.out.println();

		}

	}
}
