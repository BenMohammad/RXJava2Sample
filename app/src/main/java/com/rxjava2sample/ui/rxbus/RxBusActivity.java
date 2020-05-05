package com.rxjava2sample.ui.rxbus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.MyApplication;
import com.rxjava2sample.R;
import com.rxjava2sample.model.Events;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    private TextView textView;
    Button button;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        if(o instanceof Events.AutoEvent) {
                            textView.setText("Auto Event Received");
                        } else if (o instanceof Events.TapEvent){
                            textView.setText("Tap Event Received");
                        }
                    }
                }));

        button.setOnClickListener(v -> {
            ((MyApplication)getApplication())
                    .bus()
                    .send(new Events.TapEvent());
        });

    }
}
