package com.example.carrefour.app;

import android.app.Application;
import android.content.Context;

import com.example.carrefour.data.AppRepository;
import com.example.carrefour.data.AppRepositoryImp;
import com.example.carrefour.data.local.SharedPreference;
import com.example.carrefour.data.local.SharedPreferenceImp;
import com.example.carrefour.data.remote.AppRemoteRepo;
import com.example.carrefour.data.remote.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    AppRemoteRepo provideRemoteDataSource() {
        return ServiceGenerator.createService(AppRemoteRepo.class);
    }

    @Singleton
    @Provides
    SharedPreference provideSharedPreferenceSource(Application application) {
        return new SharedPreferenceImp(application.getApplicationContext());
    }

    @Singleton
    @Provides
    AppRepository provideAppRepository(Application application, AppRemoteRepo appRemoteRepo, SharedPreference sharedPreference) {
        return new AppRepositoryImp(application, appRemoteRepo, sharedPreference);
    }

}
