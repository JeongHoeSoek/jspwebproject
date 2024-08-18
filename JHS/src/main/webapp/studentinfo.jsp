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
<h2>학생 정보</h2>
<a href="/JHS/studentControl?action=list">새로고침</a>
<hr>
<table border="1" class="table table-striped">
  <thead>
    <tr><th>id</th><th>이름</th><th>이메일</th><th>수정</th><th>삭제</th></tr>
  </thead>
  <tbody>
    <c:forEach items="${students}" var="s">
      <tr>
        <td>${s.id}</td>
        <td>${s.username}</td>
        <td>${s.email}</td>
        <td><a href="/JHS/studentControl?action=edit&id=${s.id}">수정</a></td>
        <td><a href="/JHS/studentControl?action=delete&id=${s.id}">삭제</a></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<hr>
<h2>학생 추가</h2>
<hr>
<form method="post" action="/JHS/studentControl?action=insert">
  <div class="form-group">
    <label for="username">이름</label>
    <input type="text" class="form-control" name="username" id="username" required>
  </div>
  <div class="form-group">
    <label for="email">이메일</label>
    <input type="email" class="form-control" name="email" id="email" required>
  </div>
  <button type="submit" class="btn btn-primary">등록</button>
</form>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
