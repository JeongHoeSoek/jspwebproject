package calc;

public class CalcBean {
    private String num1;
    private String num2;
    private String operation;
    
    public String getNum1() {
        return num1;
    }
    
    public void setNum1(String num1) {
        this.num1 = num1;
    }
    
    public String getNum2() {
        return num2;
    }
    
    public void setNum2(String num2) {
        this.num2 = num2;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public double calculate() {
        double result = Double.NaN;
        
        try {
            double n1 = num1 != null ? Double.parseDouble(num1.trim()) : 0;
            double n2 = num2 != null ? Double.parseDouble(num2.trim()) : 0;
            
            if (operation != null) {
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
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException e) {
            // 유효하지 않은 입력 처리
        }
        
        return result;
    }
}
