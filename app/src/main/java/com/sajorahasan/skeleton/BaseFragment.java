package com.sajorahasan.skeleton;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {
    private static final String TAG = "BaseActivity";

    private ProgressDialog progressDialog;

    public void showLoader() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideLoader() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null)
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void print(String message) {
        if (BuildConfig.LOG_ENABLE)
            Log.d(TAG, message);
    }

    public void print(String TAG, String message) {
        if (BuildConfig.LOG_ENABLE)
            Log.d(TAG, message);
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
