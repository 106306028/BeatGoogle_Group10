<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style>
<style type="text/css">
html{
	height: 100%;
}
p {font-family: Microsoft JhengHei;}
#all{
text-align:center;
}
body{
	background-repeat:repeat-x;
	background-size:cover;
}
#header{
	width:250px;
	text-align:center;
	margin:0px auto;
	font-size:32px;
	color:#FF3333;
	font-weight:bold;
	background-color:#f9c81e;
	border-radius: 5px;
}
#Body{
	text-align:center;
	margin:0px auto;
	line-height:280px;
	font-size:15px;
	color:#ffffff;
	font-weight:bold;
}
#footer{

	text-align:center;
	margin:0px auto;
	line-height:300px;
	font-size:9px;
	font-weight:bold;
}
#search{
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
	font-family: Microsoft JhengHei
}


</style>

</head>
<body background="img/background.jpg">
<div class="bg">

<div id="header"><font face='微軟正黑體'>網路紅人大搜查</font></div>

<div id="Body">
<form action='${requestUri}' method='get'>
<input id='search' type='text' name='keyword' placeholder = '請輸入關鍵字'/> 
<input id='submit' type='submit' value='搜尋' />
</form>
</div>
<div id="footer">@Photo by Anthony DELANOIX on Unsplash.</div>
</div>

</body>
</html>