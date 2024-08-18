<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>${news.title } 수정</title>
</head>
<body>
<div class="card card-body">
    <form method="post" action="news.nhn?action=updateNews&aid=${news.aid}" enctype="multipart/form-data">
        <label class="form-label">제목</label>
        <input type="text" name="title" class="form-control" value="${news.title}">
        <label class="form-label">기사내용</label>
        <textarea cols="50" rows="5" name="content" class="form-control">${news.content}</textarea>
        <button type="submit" class="btn btn-success mt-3">수정</button>
    </form>
</div>
<hr>
<a href="javascript:history.back()" class="btn btn-primary"><< Back</a>
</body>
</html>
