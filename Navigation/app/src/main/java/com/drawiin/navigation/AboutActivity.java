package com.drawiin.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigation.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        setupUi();
    }

    private void setupUi() {
        binding.btnSubmit.setOnClickListener(
                (view) -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://cpan.ufms.br/")))
        );
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
    }

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
}