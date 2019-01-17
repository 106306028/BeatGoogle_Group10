import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class Home extends HttpServlet{
	public String name = null;
	public String subscribers = "此網紅沒有使用Youtube";
	public String likes = "此網紅沒有使用Facebook";
	public String follow = "此網紅沒有使用Instagram";
	public String youtubeLink = null;
	public String facebookLink = null;
	public String facebookTitle = null;
	public String instaLink = null;
	public String wikiLink = null;
	public Home() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		google.query();
		name = google.name;
		subscribers = "此網紅沒有使用Youtube";
		likes = "此網紅沒有使用Facebook";
		follow = "此網紅沒有使用Instagram";
		ArrayList<WebPage> webList = new ArrayList<>();
		ArrayList<Keyword> keywordList = new ArrayList<>();
		keywordList.add(new Keyword(google.searchKeyword, 1));
		keywordList.add(new Keyword("網紅", 1));
		keywordList.add(new Keyword("youtube", 1));
		keywordList.add(new Keyword("facebook", 1));
		keywordList.add(new Keyword("wiki", 2));
		keywordList.add(new Keyword("維基", 1));
		keywordList.add(new Keyword("instagram", 2));
		keywordList.add(new Keyword("百萬", 2));
		keywordList.add(new Keyword("爆紅", 3));
		keywordList.add(new Keyword("分享", 1));
		keywordList.add(new Keyword("讚", 1));		
		keywordList.add(new Keyword("訂閱", 1));


		for(int i = 0; i < google.urlList.size(); i++) {
			String url = google.urlList.get(i);
			String title = google.nameList.get(i);
			WebPage rootPage = new WebPage(url, title);
			WebTree tree = new WebTree(rootPage);
			WordCounter counter = new WordCounter(url);
			counter.findSubLink();
			for(int j = 0; j < counter.urlList.size(); j++) {
				tree.root.addChild(new WebNode(new WebPage(counter.urlList.get(j), counter.nameList.get(j))));
			}

			tree.setPostOrderScore(keywordList);
			if(url.indexOf("youtube") != -1 && url.indexOf("channel") != -1) {
				youtubeLink = url;
				WordCounter cc = new WordCounter(url);
				subscribers = cc.subscriberCount();
				tree.root.nodeScore += 500;
			}
			
			if(url.indexOf("zh-tw.facebook.com") != -1) {
				facebookLink = url;
				WordCounter cc = new WordCounter(url);
				likes = cc.facebookLike();
				int index = url.indexOf("m/");
				if(index != -1) {
					index = index + 2;
					int indexTail = url.indexOf("/", index);
					facebookTitle = url.substring(index, indexTail);
				}
				tree.root.nodeScore += 500;
			}
			
			if(url.indexOf("instagram") != -1) {
				instaLink = url;
				WordCounter cc = new WordCounter(url);
				follow = cc.follow();
				tree.root.nodeScore += 400;
			}
			
			if(url.indexOf("wikipedia") != -1) {
				wikiLink = url;
				tree.root.nodeScore += 400;
			}
			
			if(url.indexOf("weibo") != -1) {
				tree.root.nodeScore += 50;
			}
			
			if(url.indexOf("twitter") != -1) {
				tree.root.nodeScore += 50;
			}
			tree.printTree();
			webList.add(new WebPage(title, url, tree.root.nodeScore));
		}
		Collections.sort(webList, new Comparator<WebPage>() {
			
			@Override
			public int compare(WebPage web1, WebPage web2) {
				if (web1.getScore() > web2.getScore()) return -1;
		        if (web1.getScore() < web2.getScore()) return 1;
		        return 0;
			}
		});
		
		String[][] s = new String[webList.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(int i = 0; i < webList.size(); i++) {
		    String key = webList.get(i).getTitle();
		    String value = webList.get(i).getUrl();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}

		request.setAttribute("name", name);
		request.setAttribute("subscribers", subscribers);
		request.setAttribute("likes", likes);
		request.setAttribute("follow", follow);
		request.setAttribute("youtubeLink", youtubeLink);
		request.setAttribute("facebookLink", facebookLink);
		request.setAttribute("facebookTitle", facebookTitle);
		request.setAttribute("instaLink", instaLink);
		request.setAttribute("wikiLink", wikiLink);
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
