package com.rxjava2sample.model;

import androidx.annotation.NonNull;

public class ApiUser {

    public long id;
    public String firstname;
    public String lastname;

    @NonNull
    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';

    }
}
