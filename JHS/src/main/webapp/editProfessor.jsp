<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>교수 정보 수정</title>
</head>
<body>
<h2>교수 정보 수정</h2>
<hr>
<form method="post" action="/JHS/professorControl?action=update">
<input type="hidden" name="id" value="${professor.id}">
<label>이름</label>
<input type="text" name="username" value="${professor.username}"><br>
<label>이메일</label>
<input type="text" name="email" value="${professor.email}"><br>
<button type="submit">수정</button>
</form>
</body>
</html>
