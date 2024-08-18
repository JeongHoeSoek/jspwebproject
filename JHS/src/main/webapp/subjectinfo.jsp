<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>학생 정보</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<br>
<h2>과목 정보</h2>
<a href="/JHS/subjectControl?action=list">새로고침</a>
<hr>
<table border="1" class="table table-striped">
<tr><th>id</th><th>과목명</th><th>설명</th><th>수정</th><th>삭제</th></tr>
<c:forEach items="${subjects}" var="s">
	<tr>
		<td>${s.id}</td>
		<td>${s.name}</td>
		<td>${s.description}</td>
		<td><a href="/JHS/subjectControl?action=edit&id=${s.id}">수정</a></td>
		<td><a href="/JHS/subjectControl?action=delete&id=${s.id}">삭제</a></td>
	</tr>
</c:forEach>
</table>
<hr> 
<h2>과목 추가</h2>
<hr>
<form method="post" action="/JHS/subjectControl?action=insert">
<label>과목명</label>
<input type="text" name="name"><br>
<label>설명</label>
<input type="text" name="description"><br>
<button type="submit">등록</button>
</form>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
