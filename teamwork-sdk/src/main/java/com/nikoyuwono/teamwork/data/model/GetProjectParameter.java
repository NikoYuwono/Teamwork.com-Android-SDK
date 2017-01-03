package com.nikoyuwono.teamwork.data.model;

public class GetProjectParameter {

    private final ProjectStatus projectStatus;
    private final String updatedAfterDate;
    private final String updatedAfterTime;
    private final String createdAfterDate;
    private final String createdAfterTime;
    private final String categoryId;
    private final String orderBy;
    private final boolean includePeople;

    public GetProjectParameter(ProjectStatus projectStatus, String updatedAfterDate, String updatedAfterTime, String createdAfterDate, String createdAfterTime, String categoryId, String orderBy, boolean includePeople) {
        this.projectStatus = projectStatus;
        this.updatedAfterDate = updatedAfterDate;
        this.updatedAfterTime = updatedAfterTime;
        this.createdAfterDate = createdAfterDate;
        this.createdAfterTime = createdAfterTime;
        this.categoryId = categoryId;
        this.orderBy = orderBy;
        this.includePeople = includePeople;
    }

    private GetProjectParameter(Builder builder) {
        projectStatus = builder.projectStatus;
        updatedAfterDate = builder.updatedAfterDate;
        updatedAfterTime = builder.updatedAfterTime;
        createdAfterDate = builder.createdAfterDate;
        createdAfterTime = builder.createdAfterTime;
        categoryId = builder.categoryId;
        orderBy = builder.orderBy;
        includePeople = builder.includePeople;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public String getUpdatedAfterDate() {
        return updatedAfterDate;
    }

    public String getUpdatedAfterTime() {
        return updatedAfterTime;
    }

    public String getCreatedAfterDate() {
        return createdAfterDate;
    }

    public String getCreatedAfterTime() {
        return createdAfterTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean isIncludePeople() {
        return includePeople;
    }

    public static final class Builder {
        private ProjectStatus projectStatus;
        private String updatedAfterDate;
        private String updatedAfterTime;
        private String createdAfterDate;
        private String createdAfterTime;
        private String categoryId;
        private String orderBy;
        private boolean includePeople;

        private Builder() {
        }

        public Builder projectStatus(ProjectStatus projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public Builder updatedAfterDate(String updatedAfterDate) {
            this.updatedAfterDate = updatedAfterDate;
            return this;
        }

        public Builder updatedAfterTime(String updatedAfterTime) {
            this.updatedAfterTime = updatedAfterTime;
            return this;
        }

        public Builder createdAfterDate(String createdAfterDate) {
            this.createdAfterDate = createdAfterDate;
            return this;
        }

        public Builder createdAfterTime(String createdAfterTime) {
            this.createdAfterTime = createdAfterTime;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder includePeople(boolean includePeople) {
            this.includePeople = includePeople;
            return this;
        }

        public GetProjectParameter build() {
            return new GetProjectParameter(this);
        }
    }
}
