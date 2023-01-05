package br.edu.linuquiz.controller.services;

import java.util.List;

import br.edu.linuquiz.model.User;

public interface Callbacks {

    @FunctionalInterface
    interface Login{
        void onLoginCallback(boolean isSuccessful);
    }

    @FunctionalInterface
    interface Register {
        void onRegisterCallback(boolean isSuccessful);
    }

    @FunctionalInterface
    interface UserList{
        void onUserListCallback(List<User> users);
    }
}

