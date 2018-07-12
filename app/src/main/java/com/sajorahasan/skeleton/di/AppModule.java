package com.sajorahasan.skeleton.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.sajorahasan.skeleton.network.NetworkRequest;
import com.sajorahasan.skeleton.network.RestApi;
import com.sajorahasan.skeleton.room.AppDatabase;
import com.sajorahasan.skeleton.utils.Prefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context bindContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Prefs getPrefs(Context context) {
        return Prefs.with(context);
    }

    @Provides
    @Singleton
    AppDatabase getDB(Context context) {
        return AppDatabase.getInstance(context);
    }


    @Provides
    @Singleton
    RestApi getRestApi() {
        return NetworkRequest.getInstance();
    }


    @Provides
    @Singleton
    Gson getGson() {
        return new Gson();
    }


}