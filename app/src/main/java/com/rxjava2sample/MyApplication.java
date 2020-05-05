package com.rxjava2sample;

import android.app.Application;

import com.rxjava2sample.model.Events;
import com.rxjava2sample.ui.rxbus.RxBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
    }

    public RxBus bus() {
        return bus;
    }

    public void sendAutoEvent() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(aLong -> bus.send(new Events.AutoEvent()));

    }}
