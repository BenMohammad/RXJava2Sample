package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimerExampleActivity extends AppCompatActivity {

    private static final String TAG = TimerExampleActivity.class.getSimpleName();
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
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<? extends Long> getObservable() {
        return Observable.timer(2, TimeUnit.SECONDS);
    }

    private Observer<Long> getObserver() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Long aLong) {
                textView.append(" onNext: " + aLong);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }
}
