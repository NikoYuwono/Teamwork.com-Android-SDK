package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("author-id")
    private String authorId;
    @SerializedName("author-first-name")
    private String authorFirstName;
    @SerializedName("author-last-name")
    private String authorLastName;
    @SerializedName("author-avatar-url")
    private String authorAvatarUrl;
    @SerializedName("body")
    private String body;
    @SerializedName("category-id")
    private String categoryId;
    @SerializedName("posted-on")
    private String postedOn;
    @SerializedName("project-id")
    private String projectId;
    @SerializedName("comments-count")
    private String commentsCount;
    @SerializedName("attachments-count")
    private String attachmentsCount;
    @SerializedName("display-body")
    private String displayBody;
    @SerializedName("private")
    private boolean privateAccess;

    private Message(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setAuthorId(builder.authorId);
        setAuthorFirstName(builder.authorFirstName);
        setAuthorLastName(builder.authorLastName);
        setAuthorAvatarUrl(builder.authorAvatarUrl);
        setBody(builder.body);
        setCategoryId(builder.categoryId);
        setPostedOn(builder.postedOn);
        setProjectId(builder.projectId);
        setCommentsCount(builder.commentsCount);
        setAttachmentsCount(builder.attachmentsCount);
        setDisplayBody(builder.displayBody);
        setPrivateAccess(builder.privateAccess);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public void setAuthorAvatarUrl(String authorAvatarUrl) {
        this.authorAvatarUrl = authorAvatarUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(String attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public String getDisplayBody() {
        return displayBody;
    }

    public void setDisplayBody(String displayBody) {
        this.displayBody = displayBody;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public static final class Builder {
        private String id;
        private String title;
        private String authorId;
        private String authorFirstName;
        private String authorLastName;
        private String authorAvatarUrl;
        private String body;
        private String categoryId;
        private String postedOn;
        private String projectId;
        private String commentsCount;
        private String attachmentsCount;
        private String displayBody;
        private boolean privateAccess;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder authorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder authorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public Builder authorLastName(String authorLastName) {
            this.authorLastName = authorLastName;
            return this;
        }

        public Builder authorAvatarUrl(String authorAvatarUrl) {
            this.authorAvatarUrl = authorAvatarUrl;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder postedOn(String postedOn) {
            this.postedOn = postedOn;
            return this;
        }

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder commentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
            return this;
        }

        public Builder attachmentsCount(String attachmentsCount) {
            this.attachmentsCount = attachmentsCount;
            return this;
        }

        public Builder displayBody(String displayBody) {
            this.displayBody = displayBody;
            return this;
        }

        public Builder privateAccess(boolean privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", authorAvatarUrl='" + authorAvatarUrl + '\'' +
                ", body='" + body + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", postedOn='" + postedOn + '\'' +
                ", projectId='" + projectId + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", attachmentsCount='" + attachmentsCount + '\'' +
                ", displayBody='" + displayBody + '\'' +
                ", privateAccess=" + privateAccess +
                '}';
    }
}