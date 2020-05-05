package com.rxjava2sample.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User {

    public long id;
    public String firstname;
    public String lastname;
    public boolean isFollowing;

    public User(){}

    public User(ApiUser apiUser) {
        this.id = apiUser.id;
        this.firstname = apiUser.firstname;
        this.lastname = apiUser.lastname;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isFollowing=" + isFollowing +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) id + firstname.hashCode() + lastname.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof User) {
            User user = (User) obj;

            return this.id == user  .id
                    && this.firstname == user.firstname
                    && this.lastname ==user.lastname;
        }
        return false;
    }
}
