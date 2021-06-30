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
import com.drawiin.forca.utils.DialogUtils;
import com.drawiin.forca.utils.ResourcesUtils;
import com.drawiin.forca.utils.ToastUtils;

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
        viewModel.error.observe(this, error -> ToastUtils.makeShortToast(this, error));
        viewModel.hangedCount.observe(this, sticker -> binding.imgSticker.setImageDrawable(ResourcesUtils.getDrawable(this, sticker)));
        viewModel.loseGameDialog.observe(this, this::showLoseGameDialog);
        viewModel.winGameDialog.observe(this, this::showWinGameDialog);
    }

    private void showLoseGameDialog(String message) {
        DialogUtils.showDialog(
                this,
                getString(R.string.dialog_title_lose_game),
                message,
                getString(R.string.dialog_label_primary),
                getString(R.string.dialog_label_secondary),
                () -> {
                },
                this::finish
        );
    }

    private void showWinGameDialog(String message) {
        DialogUtils.showDialog(
                this,
                getString(R.string.dialog_title_win_game),
                message,
                getString(R.string.dialog_label_primary),
                getString(R.string.dialog_label_secondary),
                () -> {},
                this::finish
        );
    }

    private static final String NICKNAME = "NICKNAME";
    private static final int DEFAULT_SPANS = 8;

    public static void navigate(Context context, String nickname) {
        final Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(NICKNAME, nickname);
        context.startActivity(intent);
    }
}