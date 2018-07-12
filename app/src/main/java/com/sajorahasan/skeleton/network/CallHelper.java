package com.sajorahasan.skeleton.network;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CallHelper {

    public static <T> Disposable performAsyncRequest(Observable<T> observable, Consumer<? super T> onAction, Consumer<Throwable> onError) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onAction, onError);
    }

}


