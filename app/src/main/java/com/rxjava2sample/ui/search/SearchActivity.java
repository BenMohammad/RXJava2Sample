package com.rxjava2sample.ui.search;


import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.rxjava2sample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    public static final String TAG = SearchActivity.class.getSimpleName();
    private SearchView searchView;
    private TextView textViewResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        textViewResult = findViewById(R.id.textViewResult);

        setupSearchObservable();
    }

    private void setupSearchObservable() {
        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if(s.isEmpty()) {
                            textViewResult.setText("");
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap((Function<String, ObservableSource<String>>) s -> dataFromNetwork(s)
                        .doOnError(throwable -> {

                        })
                        .onErrorReturn(throwable -> ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        textViewResult.setText(s);
                    }
                });
    }

    private Observable<String> dataFromNetwork(final String query) {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Boolean, String>() {
                    @Override
                    public String apply(Boolean aBoolean) throws Exception {
                        return query;
                    }
                });

    }}
