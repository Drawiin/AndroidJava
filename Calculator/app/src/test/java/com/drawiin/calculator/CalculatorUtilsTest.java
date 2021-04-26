package com.drawiin.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorUtilsTest {
    CalculatorUtils calculatorUtils;

    @Before
    public void setup() {
        calculatorUtils = new CalculatorUtils();
    }

    @Test
    public void shouldReturnTrue_WhenMultiplyIsPassed() {
        boolean result = calculatorUtils.isStringOperation("*");

        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnTrue_WhenPlusIsPassed() {
        boolean result = calculatorUtils.isStringOperation("+");

        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnTrue_WhenMinusIsPassed() {
        boolean result = calculatorUtils.isStringOperation("-");

        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnTrue_WhenDivisionIsPassed() {
        boolean result = calculatorUtils.isStringOperation("/");

        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_WhenANomMathOperatorIsPassed() {
        boolean result = calculatorUtils.isStringOperation("0");

        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnASum_WhenPassingASum() {
        int result = calculatorUtils.doOperation("+", 1, 1);

        Assert.assertEquals(result, 2);
    }

    @Test
    public void shouldReturnAProduct_WhenPassingAProduct() {
        int result = calculatorUtils.doOperation("*", 2, 2);

        Assert.assertEquals(result, 4);
    }

    @Test
    public void shouldReturnASubtraction_WhenPassingASubtraction() {
        int result = calculatorUtils.doOperation("-", 6, 1);

        Assert.assertEquals(result, 5);
    }

    @Test
    public void shouldReturnADivision_WhenPassingADivision() {
        int result = calculatorUtils.doOperation("/", 8, 3);

        Assert.assertEquals(result, 2);
    }

    @Test
    public void shouldReturnTrue_WhenPassingAction() {
        Assert.assertTrue(calculatorUtils.isAction("c"));
    }

    @Test
    public void shouldReturnTrue_WhenPassingBackspaceAction() {
        Assert.assertTrue(calculatorUtils.isAction("b"));
    }

    @Test
    public void shouldReturnFalse_WhenPassingNumber() {
        Assert.assertFalse(calculatorUtils.isAction("1"));
    }

    @Test
    public void shouldReturnFalse_WhenPassingOperator() {
        Assert.assertFalse(calculatorUtils.isAction("+"));
    }

    @Test
    public void shouldReturnStringWithoutLast_String() {
        Assert.assertEquals(calculatorUtils.removeLast("123"), "12");
    }

    @Test
    public void shouldReturnStringWithoutLast_StringSingle() {
        Assert.assertEquals(calculatorUtils.removeLast("1"), "");
    }

    @Test
    public void shouldReturnStringEmpty_EmptyString() {
        Assert.assertEquals(calculatorUtils.removeLast(""), "");
    }

    @Test
    public void shouldReturnLastSubstring_WhenFullString() {
        Assert.assertEquals(calculatorUtils.getLastSubstring("13+"), "+");
    }

    @Test
    public void shouldReturnLastSubstring_WhenSingleString() {
        Assert.assertEquals(calculatorUtils.getLastSubstring("0"), "0");
    }

    @Test
    public void shouldEmpty_WhenEmptyString() {
        Assert.assertEquals(calculatorUtils.getLastSubstring(""), "");
    }

    @Test
    public void shouldReturn_ReturnStringSplit() {
        final ArrayList expected = new ArrayList(Arrays.asList("120", "12"));
        Assert.assertEquals(calculatorUtils.splitByOperation("120*12"), expected);
    }

    @Test
    public void shouldReturnTrue_WhenValidOperationIsPassed() {
        Assert.assertTrue(calculatorUtils.isValidOperation("120*12"));
    }

    @Test
    public void shouldReturnFalse_WhenInvalidOperationIsPassed() {
        Assert.assertFalse(calculatorUtils.isValidOperation("12012+"));
    }

    @Test
    public void shouldReturnFalse_WhenCantOperate() {
        Assert.assertFalse(calculatorUtils.canCalculate("12012+"));
    }

    @Test
    public void shouldReturnTrue_WhenCanOperate() {
        Assert.assertTrue(calculatorUtils.canCalculate("12012+3"));
    }


    @Test
    public void shouldReturnTrue_WhenCanOperateMoreThenOne() {
        Assert.assertTrue(calculatorUtils.canCalculate("12012+3*2"));
    }

    @Test
    public void shouldReturnCorrectValue_WhenPassingOperation() {
        Assert.assertEquals(calculatorUtils.evaluateExpression("2+2"), 4);
    }

    @Test
    public void shouldReturnCorrectValue_WhenPassingOperation2() {
        Assert.assertEquals(calculatorUtils.evaluateExpression("2+2*2"), 6);
    }
}