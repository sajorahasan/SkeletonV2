package com.sajorahasan.skeleton.di;

import com.sajorahasan.skeleton.di.scopes.PerActivity;
import com.sajorahasan.skeleton.di.scopes.PerFragment;
import com.sajorahasan.skeleton.ui.MainActivity;
import com.sajorahasan.skeleton.ui.MainFragment;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
abstract class ActivityBindingsModule {

    @PerActivity
    @ContributesAndroidInjector()
    abstract MainActivity mainActivityInjector();

    @PerFragment
    @ContributesAndroidInjector()
    abstract MainFragment mainFragment();
}
