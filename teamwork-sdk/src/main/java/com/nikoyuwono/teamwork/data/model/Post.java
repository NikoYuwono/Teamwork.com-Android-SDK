package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("category-id")
    private String categoryId;
    @SerializedName("author-id")
    private String authorId;
    @SerializedName("use-textile")
    private boolean useTextile;
    @SerializedName("milestone-id")
    private String milestoneId;
    @SerializedName("posted-on")
    private String postedOn;
    @SerializedName("project-id")
    private String projectId;
    @SerializedName("comments-count")
    private String commentsCount;
    @SerializedName("attachments-count")
    private String attachmentsCount;
    @SerializedName("private")
    private boolean privateAccess;

    private Post(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setBody(builder.body);
        setCategoryId(builder.categoryId);
        setAuthorId(builder.authorId);
        setUseTextile(builder.useTextile);
        setMilestoneId(builder.milestoneId);
        setPostedOn(builder.postedOn);
        setProjectId(builder.projectId);
        setCommentsCount(builder.commentsCount);
        setAttachmentsCount(builder.attachmentsCount);
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public boolean isUseTextile() {
        return useTextile;
    }

    public void setUseTextile(boolean useTextile) {
        this.useTextile = useTextile;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
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

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public static final class Builder {
        private String id;
        private String title;
        private String body;
        private String categoryId;
        private String authorId;
        private boolean useTextile;
        private String milestoneId;
        private String postedOn;
        private String projectId;
        private String commentsCount;
        private String attachmentsCount;
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

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder authorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder useTextile(boolean useTextile) {
            this.useTextile = useTextile;
            return this;
        }

        public Builder milestoneId(String milestoneId) {
            this.milestoneId = milestoneId;
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

        public Builder privateAccess(boolean privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}