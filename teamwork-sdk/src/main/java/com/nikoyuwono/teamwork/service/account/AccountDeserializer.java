package com.nikoyuwono.teamwork.service.account;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.nikoyuwono.teamwork.data.model.Account;

import java.lang.reflect.Type;

public class AccountDeserializer implements JsonDeserializer<Account> {
    @Override
    public Account deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonElement content = je.getAsJsonObject().get("account");
        return new Gson().fromJson(content, Account.class);

    }
}