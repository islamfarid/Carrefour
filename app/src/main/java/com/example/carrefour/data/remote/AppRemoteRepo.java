package com.example.carrefour.data.remote;


import com.example.carrefour.data.models.api.NewsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppRemoteRepo {
    @GET("everything")
    Observable<NewsResponseModel> getNewsByQuery(@Query("apiKey") String apiKey,
                                                      @Query("q") String query);
}