package com.nikoyuwono.teamwork.service.project;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.Project;

import java.lang.reflect.Type;
import java.util.List;

public class ProjectsDeserializer implements JsonDeserializer<List<Project>> {
    @Override
    public List<Project> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonElement content = je.getAsJsonObject().get("projects");
        return new Gson().fromJson(content, new TypeToken<List<Project>>() {}.getType());
    }
}
