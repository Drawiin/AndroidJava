package com.drawiin.forca.ui.register;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.drawiin.forca.R;
import com.drawiin.forca.databinding.ActivityRegisterBinding;
import com.drawiin.forca.ui.add_words.AddWordsActivity;
import com.drawiin.forca.ui.game.GameActivity;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        setupUi();
        subscribeUi();
    }

    private void setupUi() {

        binding.btnAddPhoto.setOnClickListener(view -> {
        });
        binding.btnPlay.setOnClickListener(view -> {
            final String nickname = Objects.requireNonNull(binding.inputNickname.getEditText())
                    .getText()
                    .toString();
            viewModel.onPlayClicked(nickname);
        });
        binding.btnAddWords.setOnClickListener(view -> {
            viewModel.onAddWordsClicked();
        });
    }

    private void subscribeUi() {
        viewModel.error.observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        );
        viewModel.goToGame.observe(this, nickname ->
                GameActivity.navigate(this, nickname)
        );
        viewModel.goToAddWords.observe(this, navigate ->
                AddWordsActivity.navigate(this)
        );
    }
}