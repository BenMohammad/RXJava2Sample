package com.rxjava2sample.ui.search;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    private RxSearchObservable(){}

    public static Observable<String> fromView(SearchView searchView) {
        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });

        return subject;
    }

}
