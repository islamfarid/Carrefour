package com.example.carrefour.news_details;

import android.os.Bundle;

import com.example.carrefour.R;
import com.example.carrefour.app.BaseActivity;
import com.example.carrefour.news_list.NewsListContract;

import javax.inject.Inject;

public class NewsDetailsActivity extends  BaseActivity {
    @Inject
    NewsDetailsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}