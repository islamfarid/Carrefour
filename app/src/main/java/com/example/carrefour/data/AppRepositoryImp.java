package com.example.carrefour.data;

import android.content.Context;

import com.example.carrefour.data.local.SharedPreference;
import com.example.carrefour.data.models.api.NewsResponseModel;
import com.example.carrefour.data.remote.AppRemoteRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class AppRepositoryImp implements AppRepository {
    private AppRemoteRepo mAppRemoteRepo;
    private SharedPreference mSharedPreference;
    private Context context;
    @Inject
    public AppRepositoryImp(Context context, AppRemoteRepo appRemoteRepo , SharedPreference sharedPreference) {
        this.context = context;
        this.mAppRemoteRepo = appRemoteRepo;
        this.mSharedPreference = sharedPreference;
    }

    @Override
    public Observable<NewsResponseModel> getNewsByQuery(String apiKey, String query) {
        return mAppRemoteRepo.getNewsByQuery(apiKey,query);
    }
}
