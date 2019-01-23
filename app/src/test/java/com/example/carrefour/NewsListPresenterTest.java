package com.example.carrefour;

import com.example.carrefour.news_list.NewsListBusiness;
import com.example.carrefour.news_list.NewsListContract;
import com.example.carrefour.news_list.NewsListPresenter;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewsListPresenterTest {

    private NewsListPresenter mNewsListPresenter;
    @Mock
    private NewsListBusiness mNewsListBusiness;
    @Mock
    private NewsListContract.View mNewsListView;

    @BeforeClass
    public static void setUpClass() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);    }

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        mNewsListPresenter = new NewsListPresenter(mNewsListView,mNewsListBusiness);
    }

    @Test
    public void testWhenGetArticles_ShowLoadingISCalled() {
        when(mNewsListBusiness.getUpdatedArticles()).thenReturn(Observable.create(sub -> {
            sub.onNext(new ArrayList<>());
            sub.onComplete();
        }));
        mNewsListPresenter.getUpdatedArticles(false);
        verify(mNewsListView, times(1)).showLoading();
    }
    @Test
    public void testWhenGetArticles_forSwipe_refresh_showLoadingIsNotCalled() {
        when(mNewsListBusiness.getUpdatedArticles()).thenReturn(Observable.create(sub -> {
            sub.onNext(new ArrayList<>());
            sub.onComplete();
        }));
        mNewsListPresenter.getUpdatedArticles(true);
        verify(mNewsListView, times(0)).showLoading();
    }

    @Test
    public void testWhenGetArticlesSuccess_HideLoadingISCalled() {
        when(mNewsListBusiness.getUpdatedArticles()).thenReturn(Observable.create(sub -> {
            sub.onNext(new ArrayList<>());
            sub.onComplete();
        }));
        mNewsListPresenter.getUpdatedArticles(false);
        verify(mNewsListView, times(1)).hideLoading();
    }

    @Test
    public void testWhenGetArticlesError_HideLoadingISCalled() {
        when(mNewsListBusiness.getUpdatedArticles()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
        }));
        mNewsListPresenter.getUpdatedArticles(false);
        verify(mNewsListView, times(1)).hideLoading();
    }

    @Test
    public void testWhenGetArticlesError_ShowErrorIsCalled() {
        when(mNewsListBusiness.getUpdatedArticles()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable(""));
        }));
        mNewsListPresenter.getUpdatedArticles(true);
        verify(mNewsListView, times(1)).showError("");
    }

    @AfterClass
    public static void tearDownClass() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();    }
}