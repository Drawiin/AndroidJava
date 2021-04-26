package com.drawiin.calculator;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorUtils {
    public int doOperation(String operator, int firstNumber, int secondNumber) {
        switch (operator) {
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            default:
                return 0;
        }
    }


    public String transformSymbols(String text) {
        final StringBuilder result = new StringBuilder();
        final ArrayList<String> array = new ArrayList<String>(Arrays.asList(text.split("")));
        for (String item : array) {
            if (item.equals("/")) result.append("÷");
            else if (item.equals("*")) result.append("x");
            else result.append(item);
        }
        return result.toString();
    }

    public int evaluateExpression(String expression) {
        return (int) Evaluator.eval(expression);
    }

    public String removeLast(String text) {
        final int size = text.length();
        return (size >= 1) ? text.substring(0, size - 1) : "";
    }

    public boolean canCalculate(String expression) {
        final boolean isLastNotAOperation = !isLastItemOperation(expression);
        final boolean isDisplayNotEmpty = (expression.length() >= 1);
        final boolean isValidOperation = isValidOperation(expression);
        return (isDisplayNotEmpty && isLastNotAOperation && isValidOperation);
    }

    public boolean isValidOperation(String expression) {
        return splitByOperation(expression).size() >= 2;
    }

    public ArrayList<String> splitByOperation(String text) {
        return new ArrayList<String>(Arrays.asList(text.split("[/*+-]")));
    }

    public boolean isLastItemOperation(String text) {
        return isStringOperation(getLastSubstring(text));
    }

    public String getLastSubstring(String text) {
        final int size = text.length();
        return (size >= 1) ? text.substring(size - 1, size) : "";
    }

    public boolean isANumber(String number) {
        return number.matches("[0-9]");
    }

    public boolean isStringOperation(String character) {
        return character.matches("[+*/-]");
    }

    public boolean isAction(String character) {
        return character.matches("[" + CLEAR + BACKSPACE + "]");
    }

    public static String BACKSPACE = "b";
    public static String CLEAR = "c";
    public static String EQUALS = "=";
}
