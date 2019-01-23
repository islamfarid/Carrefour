package com.example.carrefour.news_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carrefour.R;
import com.example.carrefour.common.OnListItemSelected;
import com.example.carrefour.data.models.api.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ArticlesViewHolder> {
    private Context mContext;
    OnListItemSelected<ArticlesItem> mOnListItemSelected;
    ArrayList<ArticlesItem> mArticlesList;

    NewsListAdapter(Context context, OnListItemSelected onVideoSelected, ArrayList<ArticlesItem> locationModels) {
        this.mContext = context;
        this.mArticlesList = locationModels;
        this.mOnListItemSelected = onVideoSelected;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_article_item, parent,false);
        return new ArticlesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        holder.auther.setText(mArticlesList.get(position).getAuthor());
        holder.title.setText(mArticlesList.get(position).getTitle());
        holder.date.setText(mArticlesList.get(position).getPublishedAt());
        Glide.with(mContext).load(mArticlesList.get(position).getUrlToImage()).into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return mArticlesList.size();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_item_image)
        RelativeLayout containerLayout;
        @BindView(R.id.iv_news_image)
        ImageView newsImage;
        @BindView(R.id.tv_news_author)
        TextView auther;
        @BindView(R.id.tv_list_item_date_time)
        TextView date;
        @BindView(R.id.tv_news_item_title)
        TextView title;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rl_item_image)
        void onListItemClick() {
            mOnListItemSelected.onItemSelected(mArticlesList.get(getAdapterPosition()));
        }

    }

    public void updateArticlesListItems(List<ArticlesItem> locationModels) {
        final NewsDiffCallBack diffCallback = new NewsDiffCallBack(this.mArticlesList, locationModels);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mArticlesList.clear();
        this.mArticlesList.addAll(locationModels);
        diffResult.dispatchUpdatesTo(this);
    }

}

