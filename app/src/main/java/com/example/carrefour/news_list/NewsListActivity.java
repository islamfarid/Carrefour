package com.example.carrefour.news_list;

import android.os.Bundle;

import com.example.carrefour.R;
import com.example.carrefour.app.BaseActivity;

import javax.inject.Inject;

public class NewsListActivity extends BaseActivity {
    @Inject
    NewsListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
    }
}