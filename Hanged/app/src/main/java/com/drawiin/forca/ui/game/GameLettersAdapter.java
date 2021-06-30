package com.drawiin.forca.ui.game;

import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.drawiin.forca.model.GameLetter;

public class GameLettersAdapter extends ListAdapter<GameLetter, ItemGameLetterViewHolder> {


    protected GameLettersAdapter() {
        super(new DiffUtil.ItemCallback<GameLetter>() {
            @Override
            public boolean areItemsTheSame(GameLetter oldItem, GameLetter newItem) {
                return oldItem.getLetter().equals(newItem.getLetter());
            }

            @Override
            public boolean areContentsTheSame(GameLetter oldItem, GameLetter newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @Override
    public ItemGameLetterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemGameLetterViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(ItemGameLetterViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}

