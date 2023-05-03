package com.example.newsreader;

import com.example.newsreader.models.Article;

public interface NewsItemOnClick {
    void onItemClicked(Article article,int position);
}
