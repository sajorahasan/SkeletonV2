package com.sajorahasan.skeleton.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.sajorahasan.skeleton.BaseActivity;
import com.sajorahasan.skeleton.R;
import com.sajorahasan.skeleton.network.RestApi;
import com.sajorahasan.skeleton.utils.Prefs;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    Prefs prefs;
    @Inject
    RestApi restApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast.makeText(this, "" + restApi == null ? "null" : "not null", Toast.LENGTH_LONG).show();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new MainFragment());
//        transaction.addToBackStack(new MainFragment().toString());
        transaction.commit();
    }

}
