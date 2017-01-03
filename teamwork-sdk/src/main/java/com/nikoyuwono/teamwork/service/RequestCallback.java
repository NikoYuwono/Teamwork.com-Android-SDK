package com.nikoyuwono.teamwork.service;

public interface RequestCallback<T> {

    void onGetContent(T content);
    void onError(Exception e);

}
