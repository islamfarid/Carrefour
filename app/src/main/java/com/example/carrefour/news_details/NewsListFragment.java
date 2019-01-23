package com.example.carrefour.news_details;


import com.example.carrefour.R;
import com.example.carrefour.app.BaseFragment;
import com.example.carrefour.data.models.api.ArticlesItem;

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
        setRetainInstance(true);
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
            mNewsListAdapter = new NewsListAdapter(getActivity(), item -> {
                //do some thing
            }, articlesItems);
            mRVArticles.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            mRVArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRVArticles.setAdapter(mNewsListAdapter);

        } else {
            mNewsListAdapter.updateArticlesListItems(articlesItems);
        }
    }

    @Override
    public void hideSwipeRefresh() {
        mArticlesSwipeRefresh.setRefreshing(false);
    }


}
