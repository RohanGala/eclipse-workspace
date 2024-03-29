import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Rohan Gala
 */
public class URLFinder implements Runnable {

	private String url;
	private WebCrawlerHandler linkHandler;

	public URLFinder(String url, WebCrawlerHandler handler) {
		this.url = url;
		this.linkHandler = handler;
	}

	@Override
	public void run() {
		List<String> urls = getAllLinks(url);
		if (urls != null)
			for (String l : urls) {
				try {
					if (!linkHandler.visited(l))
						linkHandler.queueLink(l);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	private List<String> getAllLinks(String url) {
		// if not already visited
		if (!linkHandler.visited(url)) {
			System.out.println(url);
			// we visited this url
			linkHandler.addVisited(url);
			List<String> urls = new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				String randomGeneratedUrl = "htp://www.times"+String.valueOf(Calendar.getInstance().getTimeInMillis())+".com";
				if (!linkHandler.visited(randomGeneratedUrl))
					urls.add(randomGeneratedUrl);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return urls;

		}
		return null;
	}
}