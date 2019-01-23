package com.example.carrefour.news_details;


import com.example.carrefour.common.EspressoIdlingResource;
import com.example.carrefour.news_list.NewsListBusiness;
import com.example.carrefour.news_list.NewsListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailsPresenter implements NewsDetailsContract.Presenter {
    private NewsDetailsContract.View mView;
    private NewsDetailsBusiness mNewsListBusiness;
    private CompositeDisposable mSubscriptions;

    @Inject
    public NewsDetailsPresenter(NewsDetailsContract.View view, NewsDetailsBusiness newsDetailsBusiness) {
        this.mView = view;
        this.mNewsListBusiness = newsDetailsBusiness;
        mSubscriptions = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
