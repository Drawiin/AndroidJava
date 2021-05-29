package com.drawiin.navigationarguments.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.drawiin.navigationarguments.R;
import com.drawiin.navigationarguments.model.Game;

public class GameDetails extends AppCompatActivity {
    private final Game game = (Game) getIntent().getSerializableExtra(GAME_EXTRA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
    }

    private static final String GAME_EXTRA = "GAME_EXTRA";

    public static void navigate(Context context, Game game) {
        final Intent intent = new Intent(context, GameDetails.class);
        intent.putExtra(GAME_EXTRA, game);
        context.startActivity(intent);
    }
}