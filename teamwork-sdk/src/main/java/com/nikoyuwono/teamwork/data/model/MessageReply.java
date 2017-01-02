package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class MessageReply {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("author-id")
    private String authorId;
    @SerializedName("author-firstname")
    private String authorFirstname;
    @SerializedName("author-lastname")
    private String authorLastname;
    @SerializedName("author-avatar-url")
    private String authorAvatarUrl;
    @SerializedName("body")
    private String body;
    @SerializedName("category-id")
    private String categoryId;
    @SerializedName("posted-on")
    private String postedOn;
    @SerializedName("messageId")
    private String messageId;
    @SerializedName("attachments-count")
    private String attachmentsCount;
    @SerializedName("comments-count")
    private String commentsCount;
    @SerializedName("private")
    private boolean privateAccess;
    @SerializedName("replyNo")
    private String replyNo;

    private MessageReply(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setAuthorId(builder.authorId);
        setAuthorFirstname(builder.authorFirstname);
        setAuthorLastname(builder.authorLastname);
        setAuthorAvatarUrl(builder.authorAvatarUrl);
        setBody(builder.body);
        setCategoryId(builder.categoryId);
        setPostedOn(builder.postedOn);
        setMessageId(builder.messageId);
        setAttachmentsCount(builder.attachmentsCount);
        setCommentsCount(builder.commentsCount);
        setPrivateAccess(builder.privateAccess);
        setReplyNo(builder.replyNo);
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

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public void setAuthorLastname(String authorLastname) {
        this.authorLastname = authorLastname;
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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(String attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public String getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(String replyNo) {
        this.replyNo = replyNo;
    }

    public static final class Builder {
        private String id;
        private String title;
        private String authorId;
        private String authorFirstname;
        private String authorLastname;
        private String authorAvatarUrl;
        private String body;
        private String categoryId;
        private String postedOn;
        private String messageId;
        private String attachmentsCount;
        private String commentsCount;
        private boolean privateAccess;
        private String replyNo;

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

        public Builder authorFirstname(String authorFirstname) {
            this.authorFirstname = authorFirstname;
            return this;
        }

        public Builder authorLastname(String authorLastname) {
            this.authorLastname = authorLastname;
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

        public Builder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder attachmentsCount(String attachmentsCount) {
            this.attachmentsCount = attachmentsCount;
            return this;
        }

        public Builder commentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
            return this;
        }

        public Builder privateAccess(boolean privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Builder replyNo(String replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public MessageReply build() {
            return new MessageReply(this);
        }
    }
}