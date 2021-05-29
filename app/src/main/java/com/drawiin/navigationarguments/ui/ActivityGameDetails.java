package com.drawiin.navigationarguments.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.drawiin.navigationarguments.R;
import com.drawiin.navigationarguments.databinding.ActivityGameDetailsBinding;
import com.drawiin.navigationarguments.model.Game;
import com.drawiin.navigationarguments.model.Modes;

import java.util.Arrays;

public class ActivityGameDetails extends AppCompatActivity {
    private ActivityGameDetailsBinding binding;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_details);
        game = (Game) getIntent().getSerializableExtra(GAME_EXTRA);
        setupUi();
    }

    private void setupUi() {
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
        binding.tvTitle.setText(game.getTitle());
        binding.tvProducer.setText(game.getProducer());
        binding.tvLaunch.setText(getString(R.string.template_launch, game.getLaunchDate()));
        binding.tvModes.setText(
                getString(R.string.template_modes,
                        Arrays.stream(game.getMode())
                                .map(Modes::getName)
                                .reduce((acc, name) -> acc + ", " + name)
                                .orElse("")
                )
        );
        binding.tvGender.setText(getString(R.string.template_gender, game.getGender().getName()));
        binding.tvPlatform.setText(getString(R.string.template_platform, game.getPlatform().getName()));
    }

    private static final String GAME_EXTRA = "GAME_EXTRA";

    public static void navigate(Context context, Game game) {
        final Intent intent = new Intent(context, ActivityGameDetails.class);
        intent.putExtra(GAME_EXTRA, game);
        context.startActivity(intent);
    }
}