package com.rxjava2sample.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.utils.AppConstant;
import com.rxjava2sample.utils.ObserverAdapter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class TakeOperatorBaseActivity extends AppCompatActivity {

    private static final String TAG = TakeOperatorBaseActivity.class.getSimpleName();
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

    protected abstract void doSomeWork();

    protected Observer<? super String> getObserver() {
        return new ObserverAdapter<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,  " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                textView.append(" onNext: " + s);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext: " + s);
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }

    protected Observable<String> getStringObservable() {
        return Observable.just("Alpha", "Beta", "Cupcake", "Doughnut", "Eclair", "Froyo", "GingerBread",
                "Honeycomb", "Ice cream sandwich");
    }
}
