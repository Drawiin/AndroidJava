package com.drawiin.forca.ui.game;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.drawiin.forca.databinding.ItemGameLetterBinding;
import com.drawiin.forca.model.GameLetter;

public class ItemGameLetterViewHolder extends RecyclerView.ViewHolder {
    final ItemGameLetterBinding binding;

    public ItemGameLetterViewHolder(ItemGameLetterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(GameLetter letter) {
        binding.tvLetter.setText(letter.getLetter());
        binding.tvLetter.setVisibility(letter.isDiscovered() ? View.VISIBLE : View.INVISIBLE);
    }

    public static ItemGameLetterViewHolder inflate(ViewGroup parent) {
        return new ItemGameLetterViewHolder(
                ItemGameLetterBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }
}
