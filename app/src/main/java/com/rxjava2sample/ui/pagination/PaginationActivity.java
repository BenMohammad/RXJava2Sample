package com.rxjava2sample.ui.pagination;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxjava2sample.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

public class PaginationActivity extends AppCompatActivity {

    public static final String TAG = PaginationActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishProcessor<Integer> paginator = PublishProcessor.create();
    private PaginationAdapter paginationAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        paginationAdapter = new PaginationAdapter();
        recyclerView.setAdapter(paginationAdapter);
        setupLoadMoreListener();
        subscribeForData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void setupLoadMoreListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
                    paginator.onNext(pageNumber);
                    loading = true;

                }
            }
        });
    }

    private void subscribeForData() {
        Disposable disposable = paginator
                .onBackpressureDrop()
                .doOnNext(page -> {
                    loading = true;
                    progressBar.setVisibility(View.VISIBLE);
                })
                .concatMapSingle(page -> dataFromNetwork(page)
                .subscribeOn(Schedulers.io())
                .doOnError(throwable -> {})
                .onErrorReturn(throwable -> new ArrayList<>()))
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(items -> {
                    paginationAdapter.addItems(items);
                    paginationAdapter.notifyDataSetChanged();

                });


        compositeDisposable.add(disposable);
        paginator.onNext(pageNumber);

    }

    private Single<List<String>> dataFromNetwork(final int page) {
        return Single.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(value -> {
                    List<String> items = new ArrayList<>();
                    for(int i = 1; i <= 10; i++) {
                        items.add("Item " + (pageNumber * 10 + i));
                    }
                    return items;
                });

    }
}
