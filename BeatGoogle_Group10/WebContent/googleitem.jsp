<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style>
<style type="text/css">
#sidebar{
	text-align:center;
	border:1px gray solid;
}
#search{
	margin-left:10px; 
	padding:5px 15px;
	border:1px black solid;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	font-family: Microsoft JhengHei;
	background-color:#fffaf3;
}
#submit{
	padding:5px 15px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	font-family: Microsoft JhengHei;
}
</style>
</head>
<body>
<div id="Header" style="height:10%;">
<a href="http://localhost:8080/BeatGoogle_Group10/Home" style="padding:5px 15px;text-decoration:none;font-family: Microsoft JhengHei;text-align:center;height:100%;width:10%; float:left;color:red;border-radius: 5px;background-color:#f9c81e;">網路紅人大搜查</a>
<form action='${requestUri}' method='get'>
<input id='search' type='text' name='keyword' placeholder = '請輸入關鍵字'/> 
<input id='submit' type='submit' value='搜尋' />
<br>
<br>
</form>
</div>
<div id="content" style="height:80%;width:55%;float:left;">
<%
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
	<font face="微軟正黑體"><a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a></font>
	<br>
	<font face="微軟正黑體" size="2" color='green'><%= orderList[i][1] %></font>
	<br>
	<br>
<%
}
%>
</div>
<div id="sidebar" style="height:80%; float:left;border-style:solid;border-width:1px; border-color:#ABABAB;padding:5px;">
<div><font face="微軟正黑體" size="5" color='blue'>網紅人氣搜查</font></div>
<div>
<%String name = (String) request.getAttribute("name"); %>
	<font face="微軟正黑體">"<%=name %>"</font>
</div>
<div>
<%String subscriber = (String) request.getAttribute("subscribers");%>
	<font face="微軟正黑體">YouTube 訂閱人數: <%=subscriber%></font>
</div>
<div>
<% String likes = (String) request.getAttribute("likes");%>
	<font face="微軟正黑體">Facebook 按讚人數: <%=likes %></font>
</div>
<div>
<%String follow = (String)request.getAttribute("follow"); %>
	<font face="微軟正黑體">Instagram 追蹤人數: <%=follow %></font>
</div>
<br>
<div style="height:80; float:left; text-align:center;"><font face="微軟正黑">傳送門 !</font></div>

<%String youtubeLink = (String) request.getAttribute("youtubeLink");
if( youtubeLink != null){%>
<div style="float:left;">
<a href="<%=youtubeLink %>"><img src="img/youtube.png" title="YouTube" width="50" height="50" border="0"></a>
</div><%}%>


<%String instaLink = (String) request.getAttribute("instaLink");
if( instaLink != null){%>
<div style="float:left;">
<a href="<%=instaLink %>"><img src="img/instagram.jpg" title="Instagram" width="50" height="50" border="0"></a>
</div><%}%>

<%String wikiLink = (String) request.getAttribute("wikiLink");
if( wikiLink != null){%>
<div style="height:80; float:left; text-align:center;">
<a href="<%=wikiLink %>"><font face="微軟正黑體">維基百科</font></a>
</div><%}%>

</div>

<%String facebookTitle = (String) request.getAttribute("facebookTitle");

if( facebookTitle != null){%>
<div style=" float:none;">
<iframe src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2F<%=facebookTitle%>%2F&tabs&width=350&height=130&small_header=false&adapt_container_width=false&hide_cover=false&show_facepile=false&appId" 
	width="350" height="130" style="border:none;overflow:hidden" 
	scrolling="no" frameborder="0" 
	allowTransparency="true" allow="encrypted-media">
</iframe>
</div>
<%}%>
<div id="Footer" style="height:10%;clear:both;"></div>
</body>
</html>