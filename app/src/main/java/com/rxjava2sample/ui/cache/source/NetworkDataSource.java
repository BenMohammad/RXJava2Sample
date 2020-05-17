package com.rxjava2sample.ui.cache.source;

import com.rxjava2sample.ui.cache.model.Data;

import io.reactivex.Observable;

public class NetworkDataSource {

    public Observable<Data> getData() {
        return Observable.create(emitter -> {
            Data data = new Data();
            data.source = "network";
            emitter.onNext(data);
            emitter.onComplete();
        }

        );
    }
}
