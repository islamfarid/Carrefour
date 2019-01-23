package com.example.carrefour.news_list;


import android.content.Intent;

import com.example.carrefour.R;
import com.example.carrefour.app.BaseFragment;
import com.example.carrefour.common.Constants;
import com.example.carrefour.data.models.api.ArticlesItem;
import com.example.carrefour.news_details.NewsDetailsActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NewsListFragment extends BaseFragment implements NewsListContract.View {
    @BindView(R.id.sr_Articles)
    SwipeRefreshLayout mArticlesSwipeRefresh;
    @BindView(R.id.rv_articles)
    RecyclerView mRVArticles;
    private NewsListAdapter mNewsListAdapter;

    @Override
    protected void init() {
        ((NewsListContract.Presenter) mPresenter).getUpdatedArticles(false);
        mArticlesSwipeRefresh.setOnRefreshListener(() -> {
            ((NewsListContract.Presenter) mPresenter).getUpdatedArticles(true);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void displayArticlesList(ArrayList<ArticlesItem> articlesItems) {
        if (mNewsListAdapter == null) {
            mNewsListAdapter = new NewsListAdapter(getActivity(), item -> goToDetailsScreen((ArticlesItem) item), articlesItems);
            mRVArticles.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            mRVArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRVArticles.setAdapter(mNewsListAdapter);

        } else {
            mNewsListAdapter.updateArticlesListItems(articlesItems);
        }
    }

    private void goToDetailsScreen(ArticlesItem item) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        intent.putExtra(Constants.ARTICLE, item);
        startActivity(intent);
    }

    @Override
    public void hideSwipeRefresh() {
        mArticlesSwipeRefresh.setRefreshing(false);
    }


}
