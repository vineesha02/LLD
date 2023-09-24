import java.util.*;

class Calculator {
    private ICalculatorOperation operation;
    private Map<String, List<String>> history;

    public Calculator() {
        history = new HashMap<>();
    }

    public void setOperation(ICalculatorOperation operation) {
        this.operation = operation;
    }

    public double calculate(double[] operands, String expression) {
        if (operation != null) {
            double result = operation.calculate(operands);
            List<String> results = history.getOrDefault(expression, new ArrayList<>());
            results.add(expression + " = " + result);
            history.put(expression, results);
            return result;
        } else {
            throw new UnsupportedOperationException("No operation selected.");
        }
    }

    public Map<String, List<String>> getHistory() {
        return history;
    }
}
