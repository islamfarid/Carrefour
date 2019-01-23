package com.example.carrefour.news_details;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carrefour.R;
import com.example.carrefour.app.BaseFragment;
import com.example.carrefour.common.Constants;
import com.example.carrefour.data.models.api.ArticlesItem;
import com.example.carrefour.news_list.NewsListAdapter;
import com.example.carrefour.news_list.NewsListContract;

import java.util.ArrayList;
import java.util.Timer;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NewsDetailsFragment extends BaseFragment implements NewsDetailsContract.View {
    @BindView(R.id.iv_news_image)
    ImageView mIVNewsImage;
    @BindView(R.id.tv_news_author)
    TextView mTVAuther;
    @BindView(R.id.tv_list_item_date_time)
    TextView mTVDate;
    @BindView(R.id.tv_news_item_title)
    TextView mTVTitle;
    @BindView(R.id.tv_news_details)
    TextView mTVNewsDetails;
    ArticlesItem mArticlesItem;

    @Override
    protected void init() {
        if (getArguments() != null && getArguments().containsKey(Constants.ARTICLE)) {
            mArticlesItem = getArguments().getParcelable(Constants.ARTICLE);
            if (mArticlesItem != null) {
                mTVAuther.setText(mArticlesItem.getAuthor());
                mTVTitle.setText(mArticlesItem.getTitle());
                mTVDate.setText(mArticlesItem.getPublishedAt());
                mTVNewsDetails.setText(mArticlesItem.getDescription());
                Glide.with(getActivity()).load(mArticlesItem.getUrlToImage()).into(mIVNewsImage);
                makeLinkableTextForArticle();
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    public static NewsDetailsFragment newInstance() {
        return new NewsDetailsFragment();
    }

    private void makeLinkableTextForArticle() {
        SpannableString ss = new SpannableString(mArticlesItem.getDescription());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mArticlesItem.getUrl()));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        int startIndex = mArticlesItem.getDescription().indexOf("…");
        try {
            ss.setSpan(clickableSpan, startIndex, startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTVNewsDetails.setText(ss);
            mTVNewsDetails.setMovementMethod(LinkMovementMethod.getInstance());
            mTVNewsDetails.setHighlightColor(Color.TRANSPARENT);
        }catch (Exception e){
            Log.e(this.getClass().getName() , "no dots in the data");
        }
    }
}
