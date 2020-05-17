package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FilterExampleActivity extends AppCompatActivity {

    private static final String TAG = FilterExampleActivity.class.getSimpleName();

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
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(integer -> integer % 2 == 0)
                .subscribe(getObserver());
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" oNext");
                textView.append(AppConstant.LINE_SEPARATOR);
                textView.append( "value:" + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " oNext");
                Log.d(TAG, " value:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                textView.append( "onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, "onError:" + e.getMessage());
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
