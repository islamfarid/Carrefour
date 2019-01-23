package com.example.carrefour.news_details.di;


import com.example.carrefour.R;
import com.example.carrefour.common.ActivityUtils;
import com.example.carrefour.data.AppRepository;
import com.example.carrefour.news_details.NewsListActivity;
import com.example.carrefour.news_details.NewsListBusiness;
import com.example.carrefour.news_details.NewsListContract;
import com.example.carrefour.news_details.NewsListFragment;
import com.example.carrefour.news_details.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class NewsListModule {

    @Provides
    NewsListContract.View provideMainView(NewsListActivity newsListActivity) {
        NewsListFragment newsListFragment =
                (NewsListFragment) newsListActivity.getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (newsListFragment == null) {
            // Create the fragment
            newsListFragment = NewsListFragment.newInstance();
            newsListFragment.setArguments(newsListActivity.getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    newsListActivity.getSupportFragmentManager(), newsListFragment, R.id.contentFrame);
        }
        return newsListFragment;
    }


    @Provides
    NewsListContract.Presenter provideVideoListPresenter(NewsListBusiness newsListBusiness, NewsListContract.View mView) {
        return new NewsListPresenter(mView, newsListBusiness);
    }

    @Provides
    NewsListBusiness provideVideoListBusiness(AppRepository appRepository) {
        return new NewsListBusiness(appRepository);
    }


}
