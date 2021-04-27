package com.drawiin.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigation.databinding.ActivitySignUpBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        setupUi();
    }

    private void setupUi() {
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
        final TextInputLayout[] inputs = new TextInputLayout[]{
                binding.user,
                binding.password,
                binding.name,
                binding.sexo,
                binding.birth,
                binding.street,
                binding.number,
                binding.postalCode,
                binding.city,
                binding.state
        };
        binding.btnSubmit.setOnClickListener((view) -> {
            for(final TextInputLayout item: inputs){
                final String text = String.valueOf(Objects.requireNonNull(item.getEditText()).getText()).trim();
                if (text.isEmpty()){
                    item.setError("Campo não pode ser vazio");
                }
                item.getEditText().setOnFocusChangeListener((a, b) -> {
                    item.setError(null);
                });
            }
        });
    }

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }
}