package com.example.newsreader.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsreader.NewsItemOnClick;
import com.example.newsreader.R;
import com.example.newsreader.databinding.NewsItemBinding;
import com.example.newsreader.models.Article;



public class NewsArticlesAdapter extends PagingDataAdapter<Article, NewsArticlesAdapter.newsviewholder> {
Context context;
NewsItemOnClick newsItemOnClick;


    public NewsArticlesAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback,Context context,NewsItemOnClick newsItemOnClick) {
        super(diffCallback);

        this.context=context;
        this.newsItemOnClick = newsItemOnClick;
    }

    @NonNull
    @Override
    public NewsArticlesAdapter.newsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new newsviewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticlesAdapter.newsviewholder holder, int position) {
    Article article = getItem(position);
        //Toast.makeText(context, "hhhhh", Toast.LENGTH_SHORT).show();
    if(article!=null){
    holder.bindArticle(article);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        newsItemOnClick.onItemClicked(getItem(position),position);
        }
    });

    }


    }


    public class newsviewholder extends RecyclerView.ViewHolder {
    NewsItemBinding newsItemBinding;

        public newsviewholder(NewsItemBinding newsItemBinding) {
            super(newsItemBinding.getRoot());
            this.newsItemBinding=newsItemBinding;
        }

        public void bindArticle(Article article) {
            newsItemBinding.setArticle(article);
            newsItemBinding.executePendingBindings();
        }
    }


}
