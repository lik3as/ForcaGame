package br.edu.linuquiz.controller.services;

public interface DAO<T> {
    void insert(T data, Callbacks callback);
    void delete(T data);
}
