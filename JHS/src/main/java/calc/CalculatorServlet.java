package calc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 요청 인코딩 설정
        response.setContentType("text/html; charset=UTF-8"); // 응답 컨텐츠 타입 및 인코딩 설정
        response.setCharacterEncoding("UTF-8"); // 응답 인코딩 설정
        
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operation = request.getParameter("operation");
        
        double result = 0;
        boolean valid = true;
        
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
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>계산 결과</title></head><body>");
        if (valid) {
            out.println("<h2>결과: " + result + "</h2>");
        } else {
            out.println("<h2>유효하지 않은 입력입니다.</h2>");
        }
        out.println("<a href='menu1.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }
}
