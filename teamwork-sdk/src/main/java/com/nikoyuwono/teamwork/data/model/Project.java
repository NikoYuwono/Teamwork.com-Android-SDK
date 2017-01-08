package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("last-changed-on")
    private String lastChangedOn;
    @SerializedName("show-announcement")
    private boolean showAnnouncement;
    @SerializedName("created-on")
    private String createdOn;
    @SerializedName("status")
    private String status;
    @SerializedName("company")
    private Company company;
    @SerializedName("announcement")
    private String announcement;
    @SerializedName("notifyEveryone")
    private boolean notifyEveryone;
    @SerializedName("starred")
    private boolean starred;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("start-page")
    private String startPage;
    @SerializedName("harvest-timers-enabled")
    private String harvestTimersEnabled;
    @SerializedName("people")
    private List<String> people;

    private Project(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setLastChangedOn(builder.lastChangedOn);
        setShowAnnouncement(builder.showAnnouncement);
        setCreatedOn(builder.createdOn);
        setStatus(builder.status);
        setCompany(builder.company);
        setAnnouncement(builder.announcement);
        setNotifyEveryone(builder.notifyEveryone);
        setStarred(builder.starred);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
        setStartPage(builder.startPage);
        setHarvestTimersEnabled(builder.harvestTimersEnabled);
        setPeople(builder.people);
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

    public String getLastChangedOn() {
        return lastChangedOn;
    }

    public void setLastChangedOn(String lastChangedOn) {
        this.lastChangedOn = lastChangedOn;
    }

    public boolean isShowAnnouncement() {
        return showAnnouncement;
    }

    public void setShowAnnouncement(boolean showAnnouncement) {
        this.showAnnouncement = showAnnouncement;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public boolean isNotifyEveryone() {
        return notifyEveryone;
    }

    public void setNotifyEveryone(boolean notifyEveryone) {
        this.notifyEveryone = notifyEveryone;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getHarvestTimersEnabled() {
        return harvestTimersEnabled;
    }

    public void setHarvestTimersEnabled(String harvestTimersEnabled) {
        this.harvestTimersEnabled = harvestTimersEnabled;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private String lastChangedOn;
        private boolean showAnnouncement;
        private String createdOn;
        private String status;
        private Company company;
        private String announcement;
        private boolean notifyEveryone;
        private boolean starred;
        private String startDate;
        private String endDate;
        private String startPage;
        private String harvestTimersEnabled;
        private List<String> people;

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

        public Builder lastChangedOn(String lastChangedOn) {
            this.lastChangedOn = lastChangedOn;
            return this;
        }

        public Builder showAnnouncement(boolean showAnnouncement) {
            this.showAnnouncement = showAnnouncement;
            return this;
        }

        public Builder createdOn(String createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public Builder announcement(String announcement) {
            this.announcement = announcement;
            return this;
        }

        public Builder notifyEveryone(boolean notifyEveryone) {
            this.notifyEveryone = notifyEveryone;
            return this;
        }

        public Builder starred(boolean starred) {
            this.starred = starred;
            return this;
        }

        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder startPage(String startPage) {
            this.startPage = startPage;
            return this;
        }

        public Builder harvestTimersEnabled(String harvestTimersEnabled) {
            this.harvestTimersEnabled = harvestTimersEnabled;
            return this;
        }

        public Builder people(List<String> people) {
            this.people = people;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    public static class Company {

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public Company(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastChangedOn='" + lastChangedOn + '\'' +
                ", showAnnouncement=" + showAnnouncement +
                ", createdOn='" + createdOn + '\'' +
                ", status='" + status + '\'' +
                ", company=" + company +
                ", announcement='" + announcement + '\'' +
                ", notifyEveryone=" + notifyEveryone +
                ", starred=" + starred +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startPage='" + startPage + '\'' +
                ", harvestTimersEnabled='" + harvestTimersEnabled + '\'' +
                '}';
    }
}