class OperationFactory {
    public static ICalculatorOperation createOperation(String operator){
        switch(operator){
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            case "/":
                return new Division();
            default:
                throw new IllegalArgumentException("Unsupported Operator: " + operator);
        }
    }
}
