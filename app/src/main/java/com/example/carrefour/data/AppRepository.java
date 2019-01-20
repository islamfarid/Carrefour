package com.example.carrefour.data;

import com.example.carrefour.data.models.api.NewsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public interface AppRepository {
    Observable<NewsResponseModel> getNewsByQuery(@Query("key") String apiKey,
                                                 @Query("q") String query);
}
