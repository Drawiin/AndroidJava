package com.drawiin.calculator;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.drawiin.calculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private MediaPlayer numberMediaPlayer;
    private MediaPlayer resultMediaPlayer;
    private MediaPlayer backMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupMediaPLayer();
        setupUi();
        subscribeUi();
    }

    private void setupMediaPLayer() {
        numberMediaPlayer = MediaPlayer.create(this, R.raw.jungle_button);
    }

    private void playButtonSound() {
        numberMediaPlayer.start();
    }

    private void setupUi() {
        binding.button0.setOnClickListener((view) -> viewModel.onActionReceived("0"));
        binding.button1.setOnClickListener((view) -> viewModel.onActionReceived("1"));
        binding.button2.setOnClickListener((view) -> viewModel.onActionReceived("2"));
        binding.button3.setOnClickListener((view) -> viewModel.onActionReceived("3"));
        binding.button4.setOnClickListener((view) -> viewModel.onActionReceived("4"));
        binding.button5.setOnClickListener((view) -> viewModel.onActionReceived("5"));
        binding.button6.setOnClickListener((view) -> viewModel.onActionReceived("6"));
        binding.button7.setOnClickListener((view) -> viewModel.onActionReceived("7"));
        binding.button8.setOnClickListener((view) -> viewModel.onActionReceived("8"));
        binding.button9.setOnClickListener((view) -> viewModel.onActionReceived("9"));
        binding.buttonClear.setOnClickListener((view) -> viewModel.onActionReceived("c"));
        binding.buttonBackspace.setOnClickListener((view) -> viewModel.onActionReceived("b"));
        binding.buttonPlus.setOnClickListener((view) -> viewModel.onActionReceived("+"));
        binding.buttonMultiply.setOnClickListener((view) -> viewModel.onActionReceived("*"));
        binding.buttonMinus.setOnClickListener((view) -> viewModel.onActionReceived("-"));
        binding.buttonDivide.setOnClickListener((view) -> viewModel.onActionReceived("/"));
        binding.buttonEquals.setOnClickListener((view) -> viewModel.onActionReceived("="));
    }

    private void subscribeUi() {
        viewModel.getDisplayFormattedValue().observe(this, (text) -> {
            binding.tvDisplay.setText(text);
        });
    }
}