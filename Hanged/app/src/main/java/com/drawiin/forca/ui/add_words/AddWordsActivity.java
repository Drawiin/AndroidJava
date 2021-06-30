package com.drawiin.forca.ui.add_words;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.drawiin.forca.R;
import com.drawiin.forca.databinding.ActivityAddWordsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddWordsActivity extends AppCompatActivity {
    private ActivityAddWordsBinding binding;
    private AddWordsViewModel viewModel;
    private AddWordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_words);
        viewModel = new ViewModelProvider(this).get(AddWordsViewModel.class);
        setupUi();
        subscribeUi();
    }

    private void setupUi() {
        binding.btnAddWords.setOnClickListener(view -> {
            final String newWord = Objects.requireNonNull(binding.inputNewWord.getEditText())
                    .getText()
                    .toString();
            viewModel.onAddWordsClicked(newWord);
            binding.inputNewWord.getEditText().setText("");
        });
        binding.toolbar.setNavigationOnClickListener(view -> finish());

        adapter = new AddWordsAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    private void subscribeUi() {
        viewModel.error.observe(this, error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show());
        viewModel.words.observe(this, words -> adapter.submitList(words));
        viewModel.success.observe(this, success -> Snackbar.make(binding.btnAddWords, success, Snackbar.LENGTH_LONG).show());
    }

    public static void navigate(Context context) {
        final Intent intent = new Intent(context, AddWordsActivity.class);
        context.startActivity(intent);
    }
}