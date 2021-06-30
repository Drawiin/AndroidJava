package com.drawiin.forca.ui.game;

import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.drawiin.forca.model.KeyboardLetter;

import java.util.function.Consumer;

public class KeyboardLettersAdapter extends ListAdapter<KeyboardLetter, ItemKeyboardLetterViewHolder> {
    final Consumer<String> onLetterClicked;


    protected KeyboardLettersAdapter(Consumer<String> onLetterClicked) {
        super(new DiffUtil.ItemCallback<KeyboardLetter>() {
            @Override
            public boolean areItemsTheSame(KeyboardLetter oldItem, KeyboardLetter newItem) {
                return oldItem.getLetter().equals(newItem.getLetter());
            }

            @Override
            public boolean areContentsTheSame(KeyboardLetter oldItem, KeyboardLetter newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.onLetterClicked = onLetterClicked;
    }

    @Override
    public ItemKeyboardLetterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemKeyboardLetterViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(ItemKeyboardLetterViewHolder holder, int position) {
        holder.bind(getItem(position), onLetterClicked);
    }
}

