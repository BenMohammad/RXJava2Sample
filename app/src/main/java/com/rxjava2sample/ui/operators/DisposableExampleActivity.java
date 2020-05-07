package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DisposableExampleActivity extends AppCompatActivity {

    private static final String TAG = DisposableExampleActivity.class.getSimpleName();
    Button button;
    TextView textView;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> doSomeWork());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    void doSomeWork() {
        disposables.add(sampleObservable()
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>(){
                    @Override
                    public void onNext(String s) {
                        textView.append(" onNext : value :" + s);
                        textView.append(AppConstant.LINE_SEPARATOR);
                        Log.d(TAG, " onNext: value :" + s);
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
                })
        );

    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                SystemClock.sleep(2000);
                return Observable.just("One", "two", "three", "four", "five");
            }
        });

    }}
