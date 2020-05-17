package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.model.User;
import com.rxjava2sample.utils.AppConstant;
import com.rxjava2sample.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZipExampleActivity extends AppCompatActivity {

    private static final String TAG = ZipExampleActivity.class.getSimpleName();
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
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                (cricketFans, footballFans) -> Utils.filterWhoLovesBoth(cricketFans, footballFans))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<List<User>> getCricketFansObservable() {
        return Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            if(!emitter.isDisposed()) {
                emitter.onNext(Utils.getUserListWhoLovesCricket());
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

    }

    private Observable<List<User>> getFootballFansObservable() {
        return Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            if(!emitter.isDisposed()) {
                emitter.onNext(Utils.getUserListWhoLovesFootball());
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

    }

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> users) {
                textView.append(" onNext: ");
                textView.append(AppConstant.LINE_SEPARATOR);
                for(User user: users) {
                    textView.append(" FirstName: " +user.firstname);
                    textView.append(AppConstant.LINE_SEPARATOR);
                }
                Log.d(TAG, " onNext: " + users.size());
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

    }}
