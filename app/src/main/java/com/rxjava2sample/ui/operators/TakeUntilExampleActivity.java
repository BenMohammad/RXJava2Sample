package com.rxjava2sample.ui.operators;

import android.util.Log;

import com.rxjava2sample.utils.AppConstant;
import com.rxjava2sample.utils.ObserverAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class TakeUntilExampleActivity extends TakeOperatorBaseActivity {

    private static final String TAG = TakeUntilExampleActivity.class.getSimpleName();

    @Override
    protected void doSomeWork() {
        Observable<Long> timerObservable = Observable.timer(5, TimeUnit.SECONDS);
        timerObservable.subscribe(new ObserverAdapter<Long>(){
            @Override
            public void onComplete() {
                String print = " Timer Completed";
                textView.append(print);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, print);
            }
        });

        getStringObservable()
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (s, aLong) -> s)
                .takeUntil(timerObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
}
