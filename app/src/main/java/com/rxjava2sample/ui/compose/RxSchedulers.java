package com.rxjava2sample.ui.compose;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {

    public <T>ObservableTransformer<T, T> applyobservableAsync() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> ObservableTransformer<T, T> applyObservableCompute() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> ObservableTransformer<T, T> applyObservableMainThread() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread());
    }

    public <T>FlowableTransformer<T, T> applyFlowableMainThread() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread());
    }

    public <T> FlowableTransformer<T, T> applyFlowableAsync() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread());
    }

    public <T> FlowableTransformer<T, T> applyFlowableCompute() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread());
    }
}
