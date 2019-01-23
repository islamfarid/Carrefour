package com.example.carrefour.app;

import com.example.carrefour.news_details.NewsDetailsActivity;
import com.example.carrefour.news_details.di.NewsDetailsModule;
import com.example.carrefour.news_list.NewsListActivity;
import com.example.carrefour.news_list.di.NewsListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = NewsListModule.class)
     abstract NewsListActivity bindNewsListActivity();
    @ContributesAndroidInjector(modules = NewsDetailsModule.class)
    abstract NewsDetailsActivity bindNewsDetailsActivity();
}
