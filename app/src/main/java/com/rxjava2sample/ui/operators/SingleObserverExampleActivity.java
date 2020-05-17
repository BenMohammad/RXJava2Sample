package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SingleObserverExampleActivity extends AppCompatActivity {

    private static final String TAG = SingleObserverExampleActivity.class.getSimpleName();
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
        Single.just("Ben")
                .subscribe(getSingleObserver());
    }

    private SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onSuccess(String s) {
                textView.append(" onNext: " + s);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError: " + e.getMessage());
            }
        };
    }
}
