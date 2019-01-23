package com.example.carrefour.app;


import android.os.Bundle;

import com.example.carrefour.common.EspressoIdlingResource;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;
import dagger.android.AndroidInjection;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }
    @Override
    protected void onDestroy() {
        ((CarrefourAPP) getApplication()).mustDie(this);
        super.onDestroy();
    }
    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
