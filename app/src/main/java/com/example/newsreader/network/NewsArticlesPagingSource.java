package com.example.newsreader.network;



import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.newsreader.models.Article;
import com.example.newsreader.models.NewsArticleResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class NewsArticlesPagingSource extends RxPagingSource<Integer, Article> {

       String country,api_key;

    public NewsArticlesPagingSource(String country,String api_key){
        this.country =country;
        this.api_key=api_key;

    }


    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Article> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Article>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try{
            int page=loadParams.getKey()!=null?loadParams.getKey():1;
             return APIClient.getRetrofit().create(APIService.class).getTopNews(page,country,api_key)
                     .subscribeOn(Schedulers.io())
                     // Map result top List of movies
                     .map(NewsArticleResponse::getArticles)
                     // Map result to LoadResult Object
                     .map(articles -> toLoadResult(articles, page))
                     // when error is there return error
                     .onErrorReturn(LoadResult.Error::new);
        }
        catch (Exception e){
            Log.d("Data",e.getLocalizedMessage());
            return Single.just(new LoadResult.Error(e));
        }






    }

    private LoadResult<Integer, Article> toLoadResult(List<Article> articles, int page) {

        Log.d("Data",String.valueOf(articles.size()));
        return new LoadResult.Page(articles, page == 1 ? null : page - 1, page + 1);
    }

}
