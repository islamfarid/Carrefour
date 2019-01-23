package com.example.carrefour.news_details.di;


import com.example.carrefour.R;
import com.example.carrefour.common.ActivityUtils;
import com.example.carrefour.data.AppRepository;
import com.example.carrefour.news_details.NewsDetailsActivity;
import com.example.carrefour.news_details.NewsDetailsBusiness;
import com.example.carrefour.news_details.NewsDetailsContract;
import com.example.carrefour.news_details.NewsDetailsFragment;
import com.example.carrefour.news_details.NewsDetailsPresenter;
import com.example.carrefour.news_list.NewsListActivity;
import com.example.carrefour.news_list.NewsListBusiness;
import com.example.carrefour.news_list.NewsListContract;
import com.example.carrefour.news_list.NewsListFragment;
import com.example.carrefour.news_list.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class NewsDetailsModule {

    @Provides
    NewsDetailsContract.View provideMainView(NewsDetailsActivity newsDetailsActivity) {
        NewsDetailsFragment fragment =
                (NewsDetailsFragment) newsDetailsActivity.getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            // Create the fragment
            fragment = NewsDetailsFragment.newInstance();
            fragment.setArguments(newsDetailsActivity.getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    newsDetailsActivity.getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        return fragment;
    }


    @Provides
    NewsDetailsContract.Presenter provideNewsDetailsPresenter(NewsDetailsBusiness newsDetailsBusiness, NewsDetailsContract.View mView) {
        return new NewsDetailsPresenter(mView, newsDetailsBusiness);
    }

    @Provides
    NewsDetailsBusiness provideNewsDetailsBusiness(AppRepository appRepository) {
        return new NewsDetailsBusiness(appRepository);
    }


}
