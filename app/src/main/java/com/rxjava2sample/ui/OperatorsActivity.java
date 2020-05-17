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
import com.rxjava2sample.ui.operators.DebounceExampleActivity;
import com.rxjava2sample.ui.operators.DeferExampleActivity;
import com.rxjava2sample.ui.operators.DelayExampleActivity;
import com.rxjava2sample.ui.operators.DisposableExampleActivity;
import com.rxjava2sample.ui.operators.DistinctExampleActivity;
import com.rxjava2sample.ui.operators.FilterExampleActivity;
import com.rxjava2sample.ui.operators.FlowableExampleActivity;
import com.rxjava2sample.ui.operators.IntervalExampleActivity;
import com.rxjava2sample.ui.operators.LastOperatorExampleActivity;
import com.rxjava2sample.ui.operators.MapExampleActivity;
import com.rxjava2sample.ui.operators.MergeExampleActivity;
import com.rxjava2sample.ui.operators.PublishSubjectExampleActivity;
import com.rxjava2sample.ui.operators.ReduceExampleActivity;


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
    }

    public void startDebounceActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DebounceExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DeferExampleActivity.class));
    }

    public void startDelayActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DelayExampleActivity.class));
    }

    public void startDisposableActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DisposableExampleActivity.class));

    }

    public void startDistinctActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DistinctExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, FilterExampleActivity.class));
    }

    public void startFlowableActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, FlowableExampleActivity.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, IntervalExampleActivity.class));
    }

    public void startLastOperatorActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, LastOperatorExampleActivity.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, MapExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, MergeExampleActivity.class));

    }

    public void startPublishSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, PublishSubjectExampleActivity.class));
    }

    public void startReduceActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ReduceExampleActivity.class));

    }}
