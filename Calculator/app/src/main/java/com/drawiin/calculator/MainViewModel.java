package com.drawiin.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import static com.drawiin.calculator.CalculatorUtils.BACKSPACE;
import static com.drawiin.calculator.CalculatorUtils.CLEAR;
import static com.drawiin.calculator.CalculatorUtils.EQUALS;

public class MainViewModel extends ViewModel {
    private final CalculatorUtils calculatorUtils = new CalculatorUtils();

    private final MutableLiveData<String> _display = new MutableLiveData<String>();
    private final LiveData<String> display = Transformations.map(_display, calculatorUtils::transformSymbols);

    public void onActionReceived(String newChar) {
        if (calculatorUtils.isANumber(newChar)) insert(newChar);
        if (calculatorUtils.isStringOperation(newChar)) onNewOperation(newChar);
        if (calculatorUtils.isAction(newChar)) onNewAction(newChar);
        if (newChar.equals(EQUALS)) onEquals();
    }

    public LiveData<String> getDisplayFormattedValue() {
        return display;
    }

    private void onEquals() {
        final String value = getCurrentDisplayValue();
        if (calculatorUtils.canCalculate(value)) {
            final int result = calculatorUtils.evaluateExpression(value);
            _display.setValue(String.valueOf(result));
        }
    }

    private void onNewAction(String action) {
        if (action.equals(CLEAR)) clearDisplay();
        if (action.equals(BACKSPACE)) deleteLast();
    }

    private void onNewOperation(String newChar) {
        final String currentValue = getCurrentDisplayValue();
        if (!calculatorUtils.isLastItemOperation(currentValue) && currentValue.length() >= 1)
            insert(newChar);
    }

    private void insert(String newChar) {
        final String currentValue = getCurrentDisplayValue();
        _display.setValue(currentValue + newChar);
    }

    private String getCurrentDisplayValue() {
        return (_display.getValue() != null) ? _display.getValue() : "";
    }

    private void clearDisplay() {
        _display.setValue("");
    }

    private void deleteLast() {
        final String currentValue = getCurrentDisplayValue();
        _display.setValue(calculatorUtils.removeLast(currentValue));
    }
}
