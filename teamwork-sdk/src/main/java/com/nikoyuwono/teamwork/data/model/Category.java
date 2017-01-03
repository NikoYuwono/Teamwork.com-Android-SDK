package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private String id;
    @SerializedName("elements_count")
    private String elementsCount;
    @SerializedName("name")
    private String name;
    @SerializedName("project-id")
    private String projectId;
    @SerializedName("type")
    private String type;
    @SerializedName("parent-id")
    private String parentId;

    private Category(Builder builder) {
        setId(builder.id);
        setElementsCount(builder.elementsCount);
        setName(builder.name);
        setProjectId(builder.projectId);
        setType(builder.type);
        setParentId(builder.parentId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(String elementsCount) {
        this.elementsCount = elementsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public static final class Builder {
        private String id;
        private String elementsCount;
        private String name;
        private String projectId;
        private String type;
        private String parentId;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder elementsCount(String elementsCount) {
            this.elementsCount = elementsCount;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder parentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", elementsCount='" + elementsCount + '\'' +
                ", name='" + name + '\'' +
                ", projectId='" + projectId + '\'' +
                ", type='" + type + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}