class Addition implements ICalculatorOperation {
public double calculate(double[] operands){
    double result = 0;
    for(double operand : operands){
        result += operand;
    }
    return result;
}
}
