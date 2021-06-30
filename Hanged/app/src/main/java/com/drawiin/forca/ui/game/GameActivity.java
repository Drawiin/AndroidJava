package com.drawiin.forca.ui.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.drawiin.forca.R;
import com.drawiin.forca.databinding.ActivityGameBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;
    private GameViewModel viewModel;
    private KeyboardLettersAdapter keyboardLettersAdapter;
    private GameLettersAdapter gameLettersAdapter;

    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        nickname = getIntent().getStringExtra(NICKNAME);
        setupUi();
        subscribeUi();
    }

    private void setupUi() {
        binding.tvUserName.setText(getString(R.string.title_hello_user, nickname));

        keyboardLettersAdapter = new KeyboardLettersAdapter(letter -> viewModel.onLetterClicked(letter));
        binding.rvLetterButtons.setLayoutManager(new GridLayoutManager(this, DEFAULT_SPANS));
        binding.rvLetterButtons.setAdapter(keyboardLettersAdapter);

        gameLettersAdapter = new GameLettersAdapter();
        binding.rvGameWords.setLayoutManager(new GridLayoutManager(this, DEFAULT_SPANS));
        binding.rvGameWords.setAdapter(gameLettersAdapter);
    }

    private void subscribeUi() {
        viewModel.keyBoardLetter.observe(this, letters -> keyboardLettersAdapter.submitList(letters));
        viewModel.gameLetter.observe(this, gameLetters -> {
            final int spanCount = Math.min(gameLetters.size(), DEFAULT_SPANS);
            binding.rvGameWords.setLayoutManager(new GridLayoutManager(this, spanCount));
            gameLettersAdapter.submitList(gameLetters);
        });
    }

    private static final String NICKNAME = "NICKNAME";
    private static final int DEFAULT_SPANS = 8;

    public static void navigate(Context context, String nickname) {
        final Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(NICKNAME, nickname);
        context.startActivity(intent);
    }
}