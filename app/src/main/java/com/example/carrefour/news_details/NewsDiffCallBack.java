package com.example.carrefour.news_details;


import com.example.carrefour.data.models.api.ArticlesItem;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class NewsDiffCallBack extends DiffUtil.Callback {

    private final List<ArticlesItem> mOldArticlList;
    private final List<ArticlesItem> mNewArticlList;

    public NewsDiffCallBack(List<ArticlesItem> oldArticlList, List<ArticlesItem> newArticlList) {
        this.mOldArticlList = oldArticlList;
        this.mNewArticlList = newArticlList;
    }

    @Override
    public int getOldListSize() {
        return mOldArticlList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewArticlList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldArticlList.get(oldItemPosition).getUrl() == mNewArticlList.get(
                newItemPosition).getUrl();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ArticlesItem oldArticlesItem = mOldArticlList.get(oldItemPosition);
        final ArticlesItem newArticlesItem = mNewArticlList.get(newItemPosition);

        return oldArticlesItem.getUrl() == newArticlesItem.getUrl();// as id is null in most cases
    }


}