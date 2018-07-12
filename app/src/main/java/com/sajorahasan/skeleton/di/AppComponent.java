package com.sajorahasan.skeleton.di;


import android.app.Application;

import com.sajorahasan.skeleton.ui.MainActivity;
import com.sajorahasan.skeleton.ui.MainFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBindingsModule.class
        }
)
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}