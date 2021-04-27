package com.drawiin.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupUi();
    }

    private void setupUi() {
        binding.btnLogin.setOnClickListener((view) -> LoginActivity.navigate(this));
        binding.btnSignUp.setOnClickListener((view) -> SignUpActivity.navigate(this));
        binding.btnAbout.setOnClickListener((view) -> AboutActivity.navigate(this));
    }

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}