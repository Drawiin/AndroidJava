package com.drawiin.asynctasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.asynctasks.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean canExecute = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupUi();
    }

    private void setupUi() {
        binding.btnStartStop.setOnClickListener(view -> {
            final Long x = Long.valueOf(getText(binding.inputX));
            final Long y = Long.valueOf(getText(binding.inputY));
            startAsyncTasks(x, y);
            onStartAsyncTasks();
        });
    }

    private void onStartAsyncTasks() {
        binding.btnStartStop.setText(R.string.btn_stop_text);
        binding.btnStartStop.setOnClickListener(view -> canExecute = false);
    }

    private String getText(TextInputLayout inputLayout) {
        return Objects.requireNonNull(inputLayout.getEditText()).getText().toString();
    }

    private void startAsyncTasks(Long x, Long y) {
        operateValue(x, value -> value * 2, this::updateXText);
        operateValue(y, value -> value * 3, this::updateYText);
    }

    private void updateXText(Long value) {
        binding.tvX.setText(getString(R.string.tv_x_value_template, value.toString()));
    }

    private void updateYText(Long value) {
        binding.tvY.setText(getString(R.string.tv_y_value_template, value.toString()));
    }

    private void operateValue(long value, Function<Long, Long> operation, Consumer<Long> onValueChanged) {
        new Thread() {
            @Override
            public void run() {
                canExecute = true;
                long currentValue = value;
                while (canExecute) {
                    currentValue = operation.apply(currentValue);
                    long finalCurrentValue = currentValue;
                    try {
                        Thread.sleep(900L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Handler(Looper.getMainLooper()).post(() -> onValueChanged.accept(finalCurrentValue));
                }
            }
        }.start();
    }

}