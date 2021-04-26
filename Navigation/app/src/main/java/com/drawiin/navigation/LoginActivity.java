package com.drawiin.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.drawiin.navigation.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    public static void navigate(Context context){
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}