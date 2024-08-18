package calc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculatorController")
public class CalculatorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operation = request.getParameter("operation");
        
        CalculatorService calcService = new CalculatorService();
        String result = calcService.calculate(num1, num2, operation);
        
        request.setAttribute("result", result);
        request.getRequestDispatcher("menu4.jsp").forward(request, response);
    }
}
