package com.drawiin.forca.ui.add_words;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.drawiin.forca.databinding.ItemWordBinding;

public class ItemWordViewHolder extends RecyclerView.ViewHolder {
    final ItemWordBinding binding;

    public ItemWordViewHolder(ItemWordBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String word) {
        binding.tvWord.setText(word);
    }

    public static ItemWordViewHolder inflate(ViewGroup parent) {
        return new ItemWordViewHolder(
                ItemWordBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }
}
