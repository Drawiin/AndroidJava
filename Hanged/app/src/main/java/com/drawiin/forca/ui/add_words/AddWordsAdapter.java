package com.drawiin.forca.ui.add_words;

import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class AddWordsAdapter extends ListAdapter<String, ItemWordViewHolder> {

    protected AddWordsAdapter() {
        super(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(String oldItem, String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(String oldItem, String newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @Override
    public ItemWordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemWordViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(ItemWordViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}

