package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = BehaviorSubjectExampleActivity.class.getSimpleName();
    Button button;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {
        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
    }

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "First onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" First onNext: " + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" First onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" First onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onComplete");
            }
        };

    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Second onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" Second onNext: " + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" Second onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" Second onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onComplete");
            }
        };

    }}
