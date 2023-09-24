public class Subtraction implements ICalculatorOperation{
    public double calculate(double[] operands){
        if(operands.length<2){
            throw new IllegalArgumentException("Subtraction requires at least two operands");
        }
        double result = operands[0];
        for(int i =1;i< operands.length;i++){
            result -= operands[i];
        }
        return result;
    }
}
