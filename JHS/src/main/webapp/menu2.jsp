<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <h2>계산기 - JSP</h2>
  <form action="menu2.jsp" method="post">
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
    String num1 = request.getParameter("num1");
    String num2 = request.getParameter("num2");
    String operation = request.getParameter("operation");
    double result = 0;
    boolean valid = true;
    
    if (num1 != null && num2 != null && operation != null) {
        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            
            switch (operation) {
                case "add":
                    result = n1 + n2;
                    break;
                case "subtract":
                    result = n1 - n2;
                    break;
                case "multiply":
                    result = n1 * n2;
                    break;
                case "divide":
                    if (n2 != 0) {
                        result = n1 / n2;
                    } else {
                        valid = false;
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            valid = false;
        }
        
        if (valid) {
            out.println("<h2>결과: " + result + "</h2>");
        } else {
            out.println("<h2>유효하지 않은 입력입니다.</h2>");
        }
    }
  %>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
