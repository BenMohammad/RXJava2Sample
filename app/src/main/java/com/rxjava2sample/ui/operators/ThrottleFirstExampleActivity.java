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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ThrottleFirstExampleActivity extends AppCompatActivity {

    private static final String TAG= ThrottleFirstExampleActivity.class.getSimpleName();
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
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(0);
                emitter.onNext(1);
                emitter.onNext(2);
                Thread.sleep(505);
                emitter.onNext(3);
                Thread.sleep(99);
                emitter.onNext(4);
                Thread.sleep(100);
                emitter.onNext(5);
                emitter.onNext(6);
                Thread.sleep(305);
                emitter.onNext(7);
                Thread.sleep(510);
                emitter.onComplete();

            }
        });
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" onNext: ");
                textView.append(AppConstant.LINE_SEPARATOR);
                textView.append(" value: " + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext: ");
                Log.d(TAG, "value: " + integer);
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
