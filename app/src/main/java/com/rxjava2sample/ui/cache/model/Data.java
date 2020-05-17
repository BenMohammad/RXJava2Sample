package com.rxjava2sample.ui.cache.model;

public class Data {

    public String source;

    @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
    public Data clone() {
        return new Data();
    }
}
