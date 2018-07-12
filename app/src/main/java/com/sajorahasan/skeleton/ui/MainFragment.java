package com.sajorahasan.skeleton.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import io.reactivex.disposables.Disposable;

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

                        long result = appDB.getDao().insert(question);
                        print("result " + result);
                        print("Size is " + appDB.getDao().countSize());

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
    }
}
