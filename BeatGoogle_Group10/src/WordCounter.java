import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordCounter {
	private String urlStr;
	private String content;
	private String contentURL;
	public ArrayList<String> urlList = new ArrayList<>();
	public ArrayList<String> nameList = new ArrayList<>();
	
	public WordCounter(String urlStr) {
		this.urlStr = urlStr;
	}
	
	private String fetchContent() throws IOException {
		String retVal = "";
		try {
		URL url = new URL(this.urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"
				+ "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
		conn.connect();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
		

		String line = null;
		
		while((line = br.readLine()) != null) {
			retVal  = retVal + line + "\n";
		}
		} catch(Exception e) {
			
		}
		
		return retVal;
		
	}
	
	public int countKeyword(String keyword) throws IOException {
		if(content == null) {
			content = fetchContent();
		}
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
		int index = 0;
		int count = 0;
		
		while(index != -1){
			index = content.indexOf(keyword);
			if(index != -1) {
				count++;
				content = content.substring(index + keyword.length());
			}
			
		}
		
		
		return count;
	}
	
	public void findSubLink() throws IOException{
		if(this.contentURL == null) {
			this.contentURL = fetchContent();
		}
		Document document = Jsoup.parse(contentURL);
		String title = document.title();
		Elements lis = document.select("a[href]");
		int count = 0;
		for(Element li : lis) {
			try {
				
				Element cite = li.select("a").get(0);
				String citeUrl = cite.attr("href");
				citeUrl = URLDecoder.decode(citeUrl.substring(citeUrl.indexOf('=') + 1, citeUrl.indexOf('&')), "UTF-8");
				
				if (!citeUrl.startsWith("https")) {
			        continue; // Ads/news/etc.
				}
				
//				int mainHead = urlStr.indexOf("//") + 2;
//				int mainTail = urlStr.substring(mainHead).indexOf("/");
//				String urlStrHead = urlStr.substring(mainHead, mainTail);
//				if (citeUrl.indexOf(urlStrHead) == -1){
//					continue;
//				}
				urlList.add(citeUrl);
				nameList.add(title);
				count++;
				if(count > 2) {
					break;
				}
			}catch(Exception e) {
				
			}
		}
		 
			
		
		
	}
	
	public String subscriberCount() throws IOException {
		if(content == null) {
			content = fetchContent();
		}
		String people = "無";
		int indexTail = content.indexOf("位訂閱");
		if(indexTail != -1) {
			int indexSkip = indexTail -10;
			content = content.substring(indexSkip);
			int indexHead = content.indexOf(">");
			if(indexHead != -1) {
				indexHead = indexHead + 1;
				indexTail = content.indexOf("<", indexHead);
				people = content.substring(indexHead, indexTail);
			}
		}

		return people + "人次";
	}
	
	public String facebookLike() throws IOException {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			
		String retVal = "";
		String line = null;
			
		while((line = br.readLine()) != null) {
			retVal  = retVal + line + "\n";
		}
		this.content = retVal;
		
		String like = "無";
		int indexTail = content.indexOf("人說這讚");
		if(indexTail != -1) {
			int indexSkip = indexTail -12;
			content = content.substring(indexSkip);
			int indexHead = content.indexOf(">");
			if(indexHead != -1) {
				indexHead = indexHead + 1;
				indexTail = content.indexOf("<", indexHead);
				like = content.substring(indexHead, indexTail);
			}
		}

		return like;
		
	}
	
	public String follow() throws IOException {
		if(content == null) {
			content = fetchContent();
		}
		String follow = "無";
		int indexTail = content.indexOf("名粉絲");
		if(indexTail != -1) {
			int indexSkip = indexTail - 10;
			int indexHead = content.indexOf("\"", indexSkip) + 1;
			follow = content.substring(indexHead, indexTail);
		}
		if(follow.equals("無")) {
		indexTail = content.indexOf("Followers");
		if(indexTail !=-1) {
			int indexSkip = indexTail -10;
			int indexHead = content.indexOf("\"", indexSkip) + 1;
			follow = content.substring(indexHead, indexTail);
		}
		}
		return follow + " 名粉絲";
	}
	
	
	public ArrayList<String> getURLList(){
		return urlList;
	}
	
	public ArrayList<String> getNameList(){
		return nameList;
	}

	
	
	
	
	
	
	
}
