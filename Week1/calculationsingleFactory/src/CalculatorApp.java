import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CalculatorApp {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter an expression (e.g. 222+33-90*4/239 or 'exit' to quit): ");
            String expression = scanner.nextLine().trim();
            if (expression.equalsIgnoreCase("exit")) {
                break;
            }
            String[] operators = {"\\+", "\\-","\\*", "\\/"};
            String operatorRegex = String.join("|", operators);
            String[] parts = expression.split("(?<=[" + operatorRegex + "])|(?=[" + operatorRegex + "])");
            List<String> tokens = new ArrayList<>();
            for (String part : parts) {
                if (!part.trim().isEmpty()) {
                    tokens.add(part.trim());
                }
            }

            List<Double> operands = new ArrayList<>();
            List<String> operatorList = new ArrayList<>();
            boolean valid = true;
            for (String token : tokens) {
                if (isOperator(token)) {
                    operatorList.add(token);
                } else {
                    try {
                        operands.add(Double.parseDouble(token));
                    } catch (NumberFormatException e) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                double[] operandArray = new double[operands.size()];
                for (int i = 0; i < operands.size(); i++) {
                    operandArray[i] = operands.get(i);
                }

                double result = operandArray[0];
                for (int i = 0; i < operatorList.size(); i++) {
                    calculator.setOperation(OperationFactory.createOperation(operatorList.get(i)));
                    result = calculator.calculate(new double[]{result, operandArray[i + 1]}, expression);
                }

                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid expression. Please enter a valid expression.");
            }
        }
        System.out.println("Calculation History:");
        clearFileContent(System.getProperty("user.dir")+"/calc_history.txt");
        Map<String, List<String>> history = calculator.getHistory();
        for (Map.Entry<String, List<String>> entry : history.entrySet()) {
            List<String> results = entry.getValue();
            saveHistoryToFile(Collections.singletonList(results.get(results.size() - 1)));
            System.out.println(results.get(results.size() - 1));
        }
        System.out.println("Calculator app exited.");
        System.out.println("You will find the calculation history here:"+ System.getProperty("user.dir")+"/calc_history.txt");
        scanner.close();
    }

    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }
    private static void saveHistoryToFile(List<String> history){
        try(PrintWriter writer = new PrintWriter(new FileWriter(System.getProperty("user.dir")+"/calc_history.txt",true))){
            for(String str : history){
                writer.println(str);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private static void clearFileContent(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}