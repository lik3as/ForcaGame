package br.edu.linuquiz.controller.services;

import java.util.List;

import br.edu.linuquiz.model.OS;
import br.edu.linuquiz.model.User;

public interface Callbacks {

    @FunctionalInterface
    interface SignUser {
        void onUserCallback(boolean isSuccessful);
    }

    @FunctionalInterface
    interface UserList{
        void onUserListCallback(List<User> users);
    }

    @FunctionalInterface
    interface GetUser{
        void onUser(User user);
    }

    @FunctionalInterface
    interface Menu{
        void onMenuClickCallback(String txt);
    }

    @FunctionalInterface
    interface OSIn{
        void onInsert(boolean isSuccessful);
    }

    @FunctionalInterface
    interface OSGet{
        void onGet(OS os);
    }

}

