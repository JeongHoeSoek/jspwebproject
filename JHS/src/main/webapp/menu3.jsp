<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="calc" class="calc.CalcBean" scope="request"/>
<jsp:setProperty name="calc" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>계산기 - JSP</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<br>
<div class="container">
  <h2>계산기 - useBean</h2>
  <form action="menu3.jsp" method="post">
    <div class="form-group">
      <label for="num1">숫자 1:</label>
      <input type="text" class="form-control" id="num1" name="num1">
    </div>
    <div class="form-group">
      <label for="num2">숫자 2:</label>
      <input type="text" class="form-control" id="num2" name="num2">
    </div>
    <div class="form-group">
      <label for="operation">연산:</label>
      <select class="form-control" id="operation" name="operation">
        <option value="add">더하기</option>
        <option value="subtract">빼기</option>
        <option value="multiply">곱하기</option>
        <option value="divide">나누기</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">계산</button>
  </form>

  <%
    double result = calc.calculate();
    if (!Double.isNaN(result)) {
        out.println("<h2>결과: " + result + "</h2>");
    } else {
        out.println("<h2>유효하지 않은 입력입니다.</h2>");
    }
  %>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
