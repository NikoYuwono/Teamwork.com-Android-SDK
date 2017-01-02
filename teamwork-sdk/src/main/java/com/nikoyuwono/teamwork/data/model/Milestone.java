package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Milestone {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("project-id")
    private String projectId;
    @SerializedName("created-on")
    private String createdOn;
    @SerializedName("creator-id")
    private String creatorId;
    @SerializedName("comments-count")
    private String commentsCount;
    @SerializedName("completed-on")
    private String completedOn;
    @SerializedName("deadline")
    private String deadline;
    @SerializedName("completed")
    private boolean completed;
    @SerializedName("completer-id")
    private String completerId;
    @SerializedName("responsible-party-ids")
    private String responsiblePartyIds;
    @SerializedName("responsible-party-names")
    private String responsiblePartyNames;
    @SerializedName("reminder")
    private boolean reminder;
    @SerializedName("private")
    private boolean privateAccess;

    private Milestone(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setProjectId(builder.projectId);
        setCreatedOn(builder.createdOn);
        setCreatorId(builder.creatorId);
        setCommentsCount(builder.commentsCount);
        setCompletedOn(builder.completedOn);
        setDeadline(builder.deadline);
        setCompleted(builder.completed);
        setCompleterId(builder.completerId);
        setResponsiblePartyIds(builder.responsiblePartyIds);
        setResponsiblePartyNames(builder.responsiblePartyNames);
        setReminder(builder.reminder);
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(String completedOn) {
        this.completedOn = completedOn;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCompleterId() {
        return completerId;
    }

    public void setCompleterId(String completerId) {
        this.completerId = completerId;
    }

    public String getResponsiblePartyIds() {
        return responsiblePartyIds;
    }

    public void setResponsiblePartyIds(String responsiblePartyIds) {
        this.responsiblePartyIds = responsiblePartyIds;
    }

    public String getResponsiblePartyNames() {
        return responsiblePartyNames;
    }

    public void setResponsiblePartyNames(String responsiblePartyNames) {
        this.responsiblePartyNames = responsiblePartyNames;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
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
        private String projectId;
        private String createdOn;
        private String creatorId;
        private String commentsCount;
        private String completedOn;
        private String deadline;
        private boolean completed;
        private String completerId;
        private String responsiblePartyIds;
        private String responsiblePartyNames;
        private boolean reminder;
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

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder createdOn(String createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder creatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public Builder commentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
            return this;
        }

        public Builder completedOn(String completedOn) {
            this.completedOn = completedOn;
            return this;
        }

        public Builder deadline(String deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder completerId(String completerId) {
            this.completerId = completerId;
            return this;
        }

        public Builder responsiblePartyIds(String responsiblePartyIds) {
            this.responsiblePartyIds = responsiblePartyIds;
            return this;
        }

        public Builder responsiblePartyNames(String responsiblePartyNames) {
            this.responsiblePartyNames = responsiblePartyNames;
            return this;
        }

        public Builder reminder(boolean reminder) {
            this.reminder = reminder;
            return this;
        }

        public Builder privateAccess(boolean privateAccess) {
            this.privateAccess = privateAccess;
            return this;
        }

        public Milestone build() {
            return new Milestone(this);
        }
    }
}