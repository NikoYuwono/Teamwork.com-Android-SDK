package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    private String id;
    @SerializedName("commentable_type")
    private String commentableType;
    @SerializedName("body")
    private String body;
    @SerializedName("author_id")
    private String authorId;
    @SerializedName("author-firstname")
    private String authorFirstname;
    @SerializedName("author-lastname")
    private String authorLastname;
    @SerializedName("author-avatar-url")
    private String authorAvatarUrl;
    @SerializedName("commentable-id")
    private String commentableId;
    @SerializedName("attachments-count")
    private String attachmentsCount;
    @SerializedName("emailed-from")
    private String emailedFrom;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("private")
    private boolean privateAccess;

    private Comment(Builder builder) {
        setId(builder.id);
        setCommentableType(builder.commentableType);
        setBody(builder.body);
        setAuthorId(builder.authorId);
        setAuthorFirstname(builder.authorFirstname);
        setAuthorLastname(builder.authorLastname);
        setAuthorAvatarUrl(builder.authorAvatarUrl);
        setCommentableId(builder.commentableId);
        setAttachmentsCount(builder.attachmentsCount);
        setEmailedFrom(builder.emailedFrom);
        setDatetime(builder.datetime);
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

    public String getCommentableType() {
        return commentableType;
    }

    public void setCommentableType(String commentableType) {
        this.commentableType = commentableType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getCommentableId() {
        return commentableId;
    }

    public void setCommentableId(String commentableId) {
        this.commentableId = commentableId;
    }

    public String getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(String attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public String getEmailedFrom() {
        return emailedFrom;
    }

    public void setEmailedFrom(String emailedFrom) {
        this.emailedFrom = emailedFrom;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public boolean isPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(boolean privateAccess) {
        this.privateAccess = privateAccess;
    }

    public static final class Builder {
        private String id;
        private String commentableType;
        private String body;
        private String authorId;
        private String authorFirstname;
        private String authorLastname;
        private String authorAvatarUrl;
        private String commentableId;
        private String attachmentsCount;
        private String emailedFrom;
        private String datetime;
        private boolean privateAccess;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder commentableType(String commentableType) {
            this.commentableType = commentableType;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
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

        public Builder commentableId(String commentableId) {
            this.commentableId = commentableId;
            return this;
        }

        public Builder attachmentsCount(String attachmentsCount) {
            this.attachmentsCount = attachmentsCount;
            return this;
        }

        public Builder emailedFrom(String emailedFrom) {
            this.emailedFrom = emailedFrom;
            return this;
        }

        public Builder datetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder privateAccess(boolean privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentableType='" + commentableType + '\'' +
                ", body='" + body + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorFirstname='" + authorFirstname + '\'' +
                ", authorLastname='" + authorLastname + '\'' +
                ", authorAvatarUrl='" + authorAvatarUrl + '\'' +
                ", commentableId='" + commentableId + '\'' +
                ", attachmentsCount='" + attachmentsCount + '\'' +
                ", emailedFrom='" + emailedFrom + '\'' +
                ", datetime='" + datetime + '\'' +
                ", privateAccess=" + privateAccess +
                '}';
    }
}