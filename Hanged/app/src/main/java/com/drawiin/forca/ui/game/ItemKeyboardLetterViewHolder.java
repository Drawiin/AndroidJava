package com.drawiin.forca.ui.game;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.recyclerview.widget.RecyclerView;

import com.drawiin.forca.R;
import com.drawiin.forca.databinding.ItemKeyboardLetterBinding;
import com.drawiin.forca.model.KeyboardLetter;
import com.drawiin.forca.utils.ResourcesUtils;

import java.util.function.Consumer;

public class ItemKeyboardLetterViewHolder extends RecyclerView.ViewHolder {
    final ItemKeyboardLetterBinding binding;

    public ItemKeyboardLetterViewHolder(ItemKeyboardLetterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(KeyboardLetter letter, Consumer<String> onLetterClicked) {
        binding.tvLetter.setText(letter.getLetter());
        binding.btnLetterContainer.setOnClickListener(view -> onLetterClicked.accept(letter.getLetter()));
        switch (letter.getState()) {
            case IDLE:
                binding.tvLetter.setTextColor(getColor(R.color.onSurface));
                binding.btnLetterContainer.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.surface)));
                binding.btnLetterContainer.setStrokeColor(getColor(R.color.onSurface));
                break;
            case WRONG:
                binding.tvLetter.setTextColor(getColor(R.color.surface));
                binding.btnLetterContainer.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.error)));
                binding.btnLetterContainer.setStrokeColor(getColor(R.color.error));
                break;
            case RIGHT:
                binding.tvLetter.setTextColor(getColor(R.color.surface));
                binding.btnLetterContainer.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.primaryColor)));
                binding.btnLetterContainer.setStrokeColor(getColor(R.color.primaryColor));
                break;
        }
    }

    @ColorInt
    private int getColor(@ColorRes int id) {
        return ResourcesUtils.getColor(binding.getRoot().getContext(), id);
    }

    public static ItemKeyboardLetterViewHolder inflate(ViewGroup parent) {
        return new ItemKeyboardLetterViewHolder(
                ItemKeyboardLetterBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }
}
