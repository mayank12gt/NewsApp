package com.example.newsreader.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.newsreader.models.Article;

public class ArticleComparator extends DiffUtil.ItemCallback<Article> {

    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }
}