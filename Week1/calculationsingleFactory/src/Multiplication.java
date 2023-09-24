class Multiplication implements ICalculatorOperation{
    public double calculate(double[] operands) {
    double result = 1;
    for(double operand : operands){
        result *= operand;
    }
    return result;
    }
}
