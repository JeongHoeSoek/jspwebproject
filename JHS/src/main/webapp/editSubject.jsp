<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과목 정보 수정</title>
</head>
<body>
<h2>과목 정보 수정</h2>
<hr>
<form method="post" action="/your_project/subjectControl?action=update">
<input type="hidden" name="id" value="${subject.id}">
<label>과목명</label>
<input type="text" name="name" value="${subject.name}"><br>
<label>설명</label>
<input type="text" name="description" value="${subject.description}"><br>
<button type="submit">수정</button>
</form>
</body>
</html>
