package com.example.newsreader.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.newsreader.Constants;
import com.example.newsreader.models.Article;
import com.example.newsreader.models.NewsArticleResponse;
import com.example.newsreader.repo.NewsRepo;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class HomeFragmentViewModel extends AndroidViewModel {

  private NewsRepo newsRepo;

    public Flowable<PagingData<Article>> articlepagingdataflowable;
  public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        newsRepo = new NewsRepo();
        getTopNews("in", Constants.API_KEY);
    }



public void getTopNews(String country, String api_key) {
        Pager<Integer, Article> pager;
        pager = newsRepo.getTopNews(country, api_key);
    articlepagingdataflowable = PagingRx.getFlowable(pager);
    CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
    PagingRx.cachedIn(articlepagingdataflowable, coroutineScope);
}
}
