package com.sajorahasan.skeleton.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sajorahasan.skeleton.BaseFragment;
import com.sajorahasan.skeleton.R;
import com.sajorahasan.skeleton.model.Question;
import com.sajorahasan.skeleton.network.CallHelper;
import com.sajorahasan.skeleton.network.RestApi;
import com.sajorahasan.skeleton.room.AppDatabase;
import com.sajorahasan.skeleton.utils.Prefs;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    @Inject
    Prefs prefs;
    @Inject
    RestApi restApi;
    @Inject
    AppDatabase appDB;
    @Inject
    Gson gson;
    private Disposable disposable;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        showLoader();

        if (isNetworkAvailable()) {
            disposable = CallHelper.performAsyncRequest(
                    restApi.getQuestionGet(1, 20, "asc", "activity", "android", "stackoverflow"), (data) -> {
                        Question question = gson.fromJson(data, Question.class);
                        print("Data:==> " + question.getQuestionItems().size());
                        hideLoader();

                        Completable.fromAction(() -> appDB.getDao().insert(question))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onComplete() {
                                        print(TAG, "onComplete: completed");
                                        print(TAG, "Size is " + appDB.getDao().countSize());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e(TAG, "onError: " + e.getLocalizedMessage());
                                    }
                                });

                    }, (error) -> {
                        print("Error:==> " + error.getMessage());
                        hideLoader();
                    });
        } else {
            showToast("Please check internet connection!!!");
            hideLoader();
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        hideLoader();
    }
}
