package com.rxjava2sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.ui.operators.AsyncSubjectExampleActivity;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startAsyncSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, AsyncSubjectExampleActivity.class));
    }
}
