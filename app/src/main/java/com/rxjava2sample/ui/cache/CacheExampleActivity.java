package com.rxjava2sample.ui.cache;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2sample.R;
import com.rxjava2sample.ui.cache.model.Data;
import com.rxjava2sample.ui.cache.source.DataSource;
import com.rxjava2sample.ui.cache.source.DiskDataSource;
import com.rxjava2sample.ui.cache.source.MemoryDataSource;
import com.rxjava2sample.ui.cache.source.NetworkDataSource;
import com.rxjava2sample.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheExampleActivity extends AppCompatActivity {

    private static final String TAG = CacheExampleActivity.class.getSimpleName();

    Button button;
    TextView textView;
    DataSource dataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> doSomeWork());
        dataSource = new DataSource(new MemoryDataSource(), new DiskDataSource(), new NetworkDataSource());
    }

    private void doSomeWork() {

        Observable<Data> memory = dataSource.getDataFromMemory();
        Observable<Data> disk = dataSource.getDataFromDisk();
        Observable<Data> network = dataSource.getDataFromNetwork();

        Observable.concat(memory, disk, network)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(getObserver());

    }

    private Observer<Data> getObserver() {
        return new Observer<Data>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Data data) {
                textView.append(" onNext :"  + data.source);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext: " + data.source);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError: " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError: " + e.getMessage());
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
