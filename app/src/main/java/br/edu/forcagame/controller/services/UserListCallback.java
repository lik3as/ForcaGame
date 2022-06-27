package br.edu.forcagame.controller.services;

import java.util.List;

import br.edu.forcagame.model.User;
@FunctionalInterface
public interface UserListCallback {
    void userList(List<User> userList);
}
