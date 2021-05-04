package com.drawiin.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
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

    /**
     * Respostas
     */
    /** 1 Crie um método para limpar os conteúdos dos componentes da interface gráfica abaixo a partir do clique no botão.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //**/
        final Button button = (Button) findViewById(R.id.clear_button);
        button.setOnClickListener((view) -> clearInputs());
    }

    private void clearInputs() {
        final EditText title = (EditText) findViewById(R.id.field_title);

        final Spinner gender = (Spinner) findViewById(R.id.field_gender);
        final Spinner platform = (Spinner) findViewById(R.id.field_platform);

        final CheckBox mode1 = (CheckBox) findViewById(R.id.field_mode1);
        final CheckBox mode2 = (CheckBox) findViewById(R.id.field_mode2);

        final EditText production = (EditText) findViewById(R.id.field_production);
        final EditText launch = (EditText) findViewById(R.id.field_launch);

        final ImageView previewPhoto = (ImageView) findViewById(R.id.field_preview_photo);

        title.setText("");

        gender.setAdapter(null);
        platform.setAdapter(null);

        if (mode1.isSelected()) mode1.toggle();
        if (mode2.isSelected()) mode2.toggle();

        production.setText("");
        launch.setText("");

        previewPhoto.setImageDrawable(null);
    }
}