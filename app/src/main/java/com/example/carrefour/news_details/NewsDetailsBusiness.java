package com.example.carrefour.news_details;

import com.example.carrefour.data.AppRepository;
import com.example.carrefour.data.models.api.ArticlesItem;

import java.util.ArrayList;

import javax.inject.Inject;

public class NewsDetailsBusiness {
    private AppRepository mAppRepository;
    private ArrayList<ArticlesItem> mCurrentArticlesItems;

    @Inject
    public NewsDetailsBusiness(AppRepository appRepository) {
        mAppRepository = appRepository;
    }


}
