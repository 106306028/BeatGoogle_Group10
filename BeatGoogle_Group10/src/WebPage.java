import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	private WordCounter counter;
	public double score = 0;
	public WebPage(String url, String name) {
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}
	
	public WebPage(String title, String url, double score) {
		this.url = url;
		this.name = title;
		this.score = score;
		
	}

	
	public void setScore(ArrayList<Keyword> keywordList) throws IOException {
		for(Keyword k : keywordList) {
			this.score += counter.countKeyword(k.name) * k.weight;
		}
	}
	
	public double getScore() {
		return score;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return name;
	}

}
