package com.example.carrefour.news_list;


import com.example.carrefour.common.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewsListContract.Presenter {
    private NewsListContract.View mView;
    private NewsListBusiness mNewsListBusiness;
    private CompositeDisposable mSubscriptions;

    @Inject
    public NewsListPresenter(NewsListContract.View view, NewsListBusiness newsListBusiness) {
        this.mView = view;
        this.mNewsListBusiness = newsListBusiness;
        mSubscriptions = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void getUpdatedArticles(boolean isSwipeToRefresh) {
        if (!isSwipeToRefresh) {
            mView.showLoading();
        }
        EspressoIdlingResource.increment();
        mSubscriptions.add(mNewsListBusiness.getUpdatedArticles().observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((articles) -> {
            mView.displayArticlesList(articles);
            if (!isSwipeToRefresh) {
                mView.hideLoading();
            } else {
                mView.hideSwipeRefresh();
            }
        }, throwable -> {
            if (!isSwipeToRefresh) {
                mView.hideLoading();
            } else {
                mView.hideSwipeRefresh();
            }
            mView.showError(throwable.getMessage());
        }));
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
