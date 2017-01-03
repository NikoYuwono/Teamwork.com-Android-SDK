package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Notebook {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("content")
    private String content;
    @SerializedName("private")
    private String privateAccess;
    @SerializedName("category-id")
    private String categoryId;
    @SerializedName("category-name")
    private String categoryName;
    @SerializedName("created-date")
    private String createdDate;
    @SerializedName("created-by-userId")
    private String createdByUserId;
    @SerializedName("created-by-userfirstname")
    private String createdByUserfirstname;
    @SerializedName("created-by-userlastname")
    private String createdByUserlastname;
    @SerializedName("locked")
    private String locked;
    @SerializedName("locked-date")
    private String lockedDate;
    @SerializedName("locked-by-userid")
    private String lockedByUserid;
    @SerializedName("locked-by-userfirstname")
    private String lockedByUserfirstname;
    @SerializedName("locked-by-userlastname")
    private String lockedByUserlastname;
    @SerializedName("project-id")
    private String projectId;

    private Notebook(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setContent(builder.content);
        setPrivateAccess(builder.privateAccess);
        setCategoryId(builder.categoryId);
        setCategoryName(builder.categoryName);
        setCreatedDate(builder.createdDate);
        setCreatedByUserId(builder.createdByUserId);
        setCreatedByUserfirstname(builder.createdByUserfirstname);
        setCreatedByUserlastname(builder.createdByUserlastname);
        setLocked(builder.locked);
        setLockedDate(builder.lockedDate);
        setLockedByUserid(builder.lockedByUserid);
        setLockedByUserfirstname(builder.lockedByUserfirstname);
        setLockedByUserlastname(builder.lockedByUserlastname);
        setProjectId(builder.projectId);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(String privateAccess) {
        this.privateAccess = privateAccess;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedByUserfirstname() {
        return createdByUserfirstname;
    }

    public void setCreatedByUserfirstname(String createdByUserfirstname) {
        this.createdByUserfirstname = createdByUserfirstname;
    }

    public String getCreatedByUserlastname() {
        return createdByUserlastname;
    }

    public void setCreatedByUserlastname(String createdByUserlastname) {
        this.createdByUserlastname = createdByUserlastname;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(String lockedDate) {
        this.lockedDate = lockedDate;
    }

    public String getLockedByUserid() {
        return lockedByUserid;
    }

    public void setLockedByUserid(String lockedByUserid) {
        this.lockedByUserid = lockedByUserid;
    }

    public String getLockedByUserfirstname() {
        return lockedByUserfirstname;
    }

    public void setLockedByUserfirstname(String lockedByUserfirstname) {
        this.lockedByUserfirstname = lockedByUserfirstname;
    }

    public String getLockedByUserlastname() {
        return lockedByUserlastname;
    }

    public void setLockedByUserlastname(String lockedByUserlastname) {
        this.lockedByUserlastname = lockedByUserlastname;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private String content;
        private String privateAccess;
        private String categoryId;
        private String categoryName;
        private String createdDate;
        private String createdByUserId;
        private String createdByUserfirstname;
        private String createdByUserlastname;
        private String locked;
        private String lockedDate;
        private String lockedByUserid;
        private String lockedByUserfirstname;
        private String lockedByUserlastname;
        private String projectId;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder privateAccess(String privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder createdDate(String createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder createdByUserId(String createdByUserId) {
            this.createdByUserId = createdByUserId;
            return this;
        }

        public Builder createdByUserfirstname(String createdByUserfirstname) {
            this.createdByUserfirstname = createdByUserfirstname;
            return this;
        }

        public Builder createdByUserlastname(String createdByUserlastname) {
            this.createdByUserlastname = createdByUserlastname;
            return this;
        }

        public Builder locked(String locked) {
            this.locked = locked;
            return this;
        }

        public Builder lockedDate(String lockedDate) {
            this.lockedDate = lockedDate;
            return this;
        }

        public Builder lockedByUserid(String lockedByUserid) {
            this.lockedByUserid = lockedByUserid;
            return this;
        }

        public Builder lockedByUserfirstname(String lockedByUserfirstname) {
            this.lockedByUserfirstname = lockedByUserfirstname;
            return this;
        }

        public Builder lockedByUserlastname(String lockedByUserlastname) {
            this.lockedByUserlastname = lockedByUserlastname;
            return this;
        }

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Notebook build() {
            return new Notebook(this);
        }
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", privateAccess='" + privateAccess + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", createdByUserfirstname='" + createdByUserfirstname + '\'' +
                ", createdByUserlastname='" + createdByUserlastname + '\'' +
                ", locked='" + locked + '\'' +
                ", lockedDate='" + lockedDate + '\'' +
                ", lockedByUserid='" + lockedByUserid + '\'' +
                ", lockedByUserfirstname='" + lockedByUserfirstname + '\'' +
                ", lockedByUserlastname='" + lockedByUserlastname + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}