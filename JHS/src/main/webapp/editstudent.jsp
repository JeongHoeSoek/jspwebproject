<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 수정</title>
</head>
<body>
<h2>학생 정보 수정</h2>
<hr>
<form method="post" action="/JHS/studentControl?action=update">
<input type="hidden" name="id" value="${student.id}">
<label>이름</label>
<input type="text" name="username" value="${student.username}"><br>
<label>이메일</label>
<input type="text" name="email" value="${student.email}"><br>
<button type="submit">수정</button>
</form>
</body>
</html>
