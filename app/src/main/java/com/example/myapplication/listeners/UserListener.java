package com.example.myapplication.listeners;

import com.example.myapplication.models.Users.User;

public interface UserListener {
     default void onUserClicked(User user)
    {

    };
}
