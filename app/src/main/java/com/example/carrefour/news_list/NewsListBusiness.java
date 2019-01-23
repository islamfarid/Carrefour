package com.example.carrefour.news_list;

import com.example.carrefour.common.Constants;
import com.example.carrefour.data.AppRepository;
import com.example.carrefour.data.models.api.ArticlesItem;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NewsListBusiness {
    private AppRepository mAppRepository;
    private ArrayList<ArticlesItem> mCurrentArticlesItems;

    @Inject
    public NewsListBusiness(AppRepository appRepository) {
        mAppRepository = appRepository;
    }


    public Observable<ArrayList<ArticlesItem>> getUpdatedArticles() {
        return mAppRepository.getNewsByQuery(Constants.API_KEY,Constants.SEARCH_DATA ).map(articlesData -> {
            if (articlesData == null || articlesData.getArticles() == null || articlesData.getArticles().isEmpty()) {
                mCurrentArticlesItems = new ArrayList<>();
            } else {
                mCurrentArticlesItems = articlesData.getArticles();
            }
            return mCurrentArticlesItems;
        });
    }



}
