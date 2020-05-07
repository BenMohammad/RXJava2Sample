package com.rxjava2sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.ui.operators.AsyncSubjectExampleActivity;
import com.rxjava2sample.ui.operators.BehaviorSubjectExampleActivity;
import com.rxjava2sample.ui.operators.BufferExampleActivity;
import com.rxjava2sample.ui.operators.CompletableObserverExampleActivity;
import com.rxjava2sample.ui.operators.ConcatExampleActivity;


public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startAsyncSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, AsyncSubjectExampleActivity.class));
    }

    public void startBehaviorSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, BehaviorSubjectExampleActivity.class));
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, BufferExampleActivity.class));
    }

    public void startCompletableObserverActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, CompletableObserverExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ConcatExampleActivity.class));

    }}
