public class Division implements ICalculatorOperation{
    public double calculate(double[] operands){
        if(operands.length<2){
            throw new IllegalArgumentException("Division Operation requires atleast two operands");
        }
        double result = operands[0];
        for (int i =1;i<operands.length;i++){
            if(operands[i] == 0){
                System.out.println("Error : Division by Zero");
                return Double.NaN;
            }
            result /= operands[i];
        }
        return result;
    }
}
