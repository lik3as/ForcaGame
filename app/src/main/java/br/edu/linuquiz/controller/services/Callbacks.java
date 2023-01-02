package br.edu.linuquiz.controller.services;

import java.util.List;

import br.edu.linuquiz.model.User;

public interface Callbacks {

    interface Login{
        void onLoginCallback(boolean isSuccessful);
    }
    interface Register {
        void onRegisterCallback(boolean isSuccessful);
    }
    interface UserList{
        void onUserListCallback(List<User> users);
    }
}

