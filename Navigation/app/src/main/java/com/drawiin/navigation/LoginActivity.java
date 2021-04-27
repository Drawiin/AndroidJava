package com.drawiin.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableInt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.drawiin.navigation.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setupUi();
    }

    private void setupUi() {
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
        final TextInputLayout[] inputs = new TextInputLayout[]{
                binding.user,
                binding.password,
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

    public static void navigate(Context context){
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}