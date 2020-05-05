package com.rxjava2sample.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rxjava2sample.MyApplication;
import com.rxjava2sample.R;
import com.rxjava2sample.ui.rxbus.RxBusActivity;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void startRxBusActivity(View view) {
        ((MyApplication) getApplication()).sendAutoEvent();
        startActivity(new Intent(SelectionActivity.this, RxBusActivity.class));
    }
}
