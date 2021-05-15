package com.drawiin.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigation.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private TextInputLayout[] inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setupUi();
        if (savedInstanceState != null) recoveryState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        for (final TextInputLayout item : inputs) {
            final String key = String.valueOf(item.getId());
            final String text = String.valueOf(Objects.requireNonNull(item.getEditText()).getText()).trim();
            Log.i(TAG, "Saving State " + text);
            outState.putString(key, text);
        }
        super.onSaveInstanceState(outState);
    }

    private void setupUi() {
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
        inputs = new TextInputLayout[]{
                binding.user,
                binding.password,
        };
        binding.btnSubmit.setOnClickListener((view) -> {
            for (final TextInputLayout item : inputs) {
                final String text = String.valueOf(Objects.requireNonNull(item.getEditText()).getText()).trim();
                if (text.isEmpty()) {
                    item.setError("Campo não pode ser vazio");
                }
                item.getEditText().setOnFocusChangeListener((a, b) -> {
                    item.setError(null);
                });
            }
        });
    }

    private void recoveryState(Bundle savedInstanceState) {
        for (final TextInputLayout item : inputs) {
            final String key = String.valueOf(item.getId());

            final String recovery = savedInstanceState.getString(key);
            Log.i(TAG, "Recovering State " + recovery);
            Objects.requireNonNull(item.getEditText()).setText(recovery);
        }
    }

    private static final String TAG = "LOG_IN_ACTIVITY";

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}