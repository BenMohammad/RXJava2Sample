package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BufferExampleActivity extends AppCompatActivity {

    private static final String TAG = BufferExampleActivity.class.getSimpleName();
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {
        Observable<List<String>> buffered = getObservable().buffer(3, 1);

        buffered.subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five");
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> strings) {
                textView.append(" onNext size : " + strings.size());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext :  size : " + strings.size());
                for(String value : strings) {
                    textView.append(" value : " + value);
                    textView.append(AppConstant.LINE_SEPARATOR);
                    Log.d(TAG, ": value : " + value);

                }
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError ; " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }
}
