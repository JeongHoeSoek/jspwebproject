package calc;

public class CalculatorService {
    public String calculate(String num1, String num2, String operation) {
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
        
        if (valid) {
            return String.valueOf(result);
        } else {
            return "유효하지 않은 입력입니다.";
        }
    }
}
