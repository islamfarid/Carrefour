package com.example.carrefour;

import android.os.Bundle;
import android.widget.TextView;

import com.example.carrefour.common.Constants;
import com.example.carrefour.data.AppRepositoryImp;
import com.example.carrefour.data.local.SharedPreferenceImp;
import com.example.carrefour.data.remote.AppRemoteRepo;
import com.example.carrefour.data.remote.ServiceGenerator;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // for testing api integration only
        new AppRepositoryImp(this, ServiceGenerator.
                createService(AppRemoteRepo.class),new SharedPreferenceImp(this)).
                getNewsByQuery(Constants.API_KEY , "android").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(newsResponseModel -> {
                    ((TextView)findViewById(R.id.text)).setText(new Gson().toJson(newsResponseModel));
                });
    }
}
