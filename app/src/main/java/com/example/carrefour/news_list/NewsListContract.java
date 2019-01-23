package com.example.carrefour.news_list;



import com.example.carrefour.app.BasePresenter;
import com.example.carrefour.app.BaseView;
import com.example.carrefour.data.models.api.ArticlesItem;

import java.util.ArrayList;


public interface NewsListContract {
    interface View extends BaseView<BasePresenter> {
        void displayArticlesList(ArrayList<ArticlesItem> articlesItems);

        void hideSwipeRefresh();
    }

    interface Presenter extends BasePresenter {

        void getUpdatedArticles(boolean isSwipeToRefresh);

    }
}
