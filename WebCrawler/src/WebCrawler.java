import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashSet;

/**
 *
 * @author Rohan Gala
 */
public class WebCrawler implements WebCrawlerHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    private String url;
    private ExecutorService execService;

    public WebCrawler(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public void queueLink(String link) throws Exception {
        submitTask(link);
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

   

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private void submitTask(String link) throws Exception {
        execService.execute(new URLFinder(link, this));
    }

    private void startCrawling() throws Exception {
        submitTask(this.url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WebCrawler("http://www.timesofIndia.com", 4).startCrawling();
    }
}
