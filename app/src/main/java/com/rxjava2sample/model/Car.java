package com.rxjava2sample.model;


import io.reactivex.Observable;


public class Car {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Observable<String> brandDeferObservable() {
        return Observable.defer(() -> Observable.just(brand));
    }
}
