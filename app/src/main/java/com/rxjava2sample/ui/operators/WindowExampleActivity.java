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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WindowExampleActivity extends AppCompatActivity {


    private static final String TAG = WindowExampleActivity.class.getSimpleName();
    Button button;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        button= findViewById(R.id.btn);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {
        Observable.interval(1, TimeUnit.SECONDS).take(12)
                .window(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getConsumer());
    }

    public Consumer<Observable<Long>> getConsumer() {
        return new Consumer<Observable<Long>>() {
            @Override
            public void accept(Observable<Long> longObservable) throws Exception {
                Log.d(TAG, "Sub divide begin...");
                textView.append("Sub Divide begins....");
                textView.append(AppConstant.LINE_SEPARATOR);
                longObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            Log.d(TAG, "Next: " + aLong);
                            textView.append("Next: " + aLong);
                            textView.append(AppConstant.LINE_SEPARATOR);
                        });
            }
        };
    }
}
