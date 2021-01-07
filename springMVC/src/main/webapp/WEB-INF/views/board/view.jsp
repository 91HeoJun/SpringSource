<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결과물</h1>
	
	<hr />
	<h2>My Style</h2>
	<h2>작성자 : ${words.writer}</h2>
	<h2>제목 : ${words.title}</h2>
	<br />
	<h3>내용</h3>
	<h3>${words.content}</h3>
	<br />
	<h4>비밀번호 : ${words.password}</h4>

</body>
</html>