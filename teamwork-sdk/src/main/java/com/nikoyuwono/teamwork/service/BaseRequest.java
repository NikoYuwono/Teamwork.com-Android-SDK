package com.nikoyuwono.teamwork.service;

import com.google.gson.Gson;
import com.nikoyuwono.teamwork.data.net.ApiClient;

public abstract class BaseRequest<T> {

    protected Gson gson;
    protected T service;

    public abstract void init(final ApiClient apiClient);
}
