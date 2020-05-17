package com.rxjava2sample.ui.operators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;

public class TakeWhileExampleActivity extends TakeOperatorBaseActivity {


    private static final String TAG = TakeWhileExampleActivity.class.getSimpleName();

    @Override
    protected void doSomeWork() {
        getStringObservable()

                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (s, aLong) -> s)

                .takeWhile(s -> !s.toLowerCase().contains("honey"))

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
}
