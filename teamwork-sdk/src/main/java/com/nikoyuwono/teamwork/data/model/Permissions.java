package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Permissions {

    @SerializedName("view-messages-and-files")
    private String viewMessagesAndFiles;
    @SerializedName("view-tasks-and-milestones")
    private String viewTasksAndMilestones;
    @SerializedName("view-time")
    private String viewTime;
    @SerializedName("view-notebooks")
    private String viewNotebooks;
    @SerializedName("view-risk-register")
    private String viewRiskRegister;
    @SerializedName("view-invoices")
    private String viewInvoices;
    @SerializedName("view-links")
    private String viewLinks;
    @SerializedName("add-tasks")
    private String addTasks;
    @SerializedName("add-milestones")
    private String addMilestones;
    @SerializedName("add-taskLists")
    private String addTaskLists;
    @SerializedName("add-messages")
    private String addMessages;
    @SerializedName("add-files")
    private String addFiles;
    @SerializedName("add-time")
    private String addTime;
    @SerializedName("add-notebooks")
    private String addNotebooks;
    @SerializedName("add-links")
    private String addLinks;
    @SerializedName("set-privacy")
    private String setPrivacy;
    @SerializedName("can-be-assigned-to-tasks-and-milestones")
    private String canBeAssignedToTasksAndMilestones;
    @SerializedName("project-administrator")
    private String projectAdministrator;
    @SerializedName("add-people-to-project")
    private String addPeopleToProject;

    private Permissions(Builder builder) {
        setViewMessagesAndFiles(builder.viewMessagesAndFiles);
        setViewTasksAndMilestones(builder.viewTasksAndMilestones);
        setViewTime(builder.viewTime);
        setViewNotebooks(builder.viewNotebooks);
        setViewRiskRegister(builder.viewRiskRegister);
        setViewInvoices(builder.viewInvoices);
        setViewLinks(builder.viewLinks);
        setAddTasks(builder.addTasks);
        setAddMilestones(builder.addMilestones);
        setAddTaskLists(builder.addTaskLists);
        setAddMessages(builder.addMessages);
        setAddFiles(builder.addFiles);
        setAddTime(builder.addTime);
        setAddNotebooks(builder.addNotebooks);
        setAddLinks(builder.addLinks);
        setSetPrivacy(builder.setPrivacy);
        setCanBeAssignedToTasksAndMilestones(builder.canBeAssignedToTasksAndMilestones);
        setProjectAdministrator(builder.projectAdministrator);
        setAddPeopleToProject(builder.addPeopleToProject);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getViewMessagesAndFiles() {
        return viewMessagesAndFiles;
    }

    public void setViewMessagesAndFiles(String viewMessagesAndFiles) {
        this.viewMessagesAndFiles = viewMessagesAndFiles;
    }

    public String getViewTasksAndMilestones() {
        return viewTasksAndMilestones;
    }

    public void setViewTasksAndMilestones(String viewTasksAndMilestones) {
        this.viewTasksAndMilestones = viewTasksAndMilestones;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    public String getViewNotebooks() {
        return viewNotebooks;
    }

    public void setViewNotebooks(String viewNotebooks) {
        this.viewNotebooks = viewNotebooks;
    }

    public String getViewRiskRegister() {
        return viewRiskRegister;
    }

    public void setViewRiskRegister(String viewRiskRegister) {
        this.viewRiskRegister = viewRiskRegister;
    }

    public String getViewInvoices() {
        return viewInvoices;
    }

    public void setViewInvoices(String viewInvoices) {
        this.viewInvoices = viewInvoices;
    }

    public String getViewLinks() {
        return viewLinks;
    }

    public void setViewLinks(String viewLinks) {
        this.viewLinks = viewLinks;
    }

    public String getAddTasks() {
        return addTasks;
    }

    public void setAddTasks(String addTasks) {
        this.addTasks = addTasks;
    }

    public String getAddMilestones() {
        return addMilestones;
    }

    public void setAddMilestones(String addMilestones) {
        this.addMilestones = addMilestones;
    }

    public String getAddTaskLists() {
        return addTaskLists;
    }

    public void setAddTaskLists(String addTaskLists) {
        this.addTaskLists = addTaskLists;
    }

    public String getAddMessages() {
        return addMessages;
    }

    public void setAddMessages(String addMessages) {
        this.addMessages = addMessages;
    }

    public String getAddFiles() {
        return addFiles;
    }

    public void setAddFiles(String addFiles) {
        this.addFiles = addFiles;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddNotebooks() {
        return addNotebooks;
    }

    public void setAddNotebooks(String addNotebooks) {
        this.addNotebooks = addNotebooks;
    }

    public String getAddLinks() {
        return addLinks;
    }

    public void setAddLinks(String addLinks) {
        this.addLinks = addLinks;
    }

    public String getSetPrivacy() {
        return setPrivacy;
    }

    public void setSetPrivacy(String setPrivacy) {
        this.setPrivacy = setPrivacy;
    }

    public String getCanBeAssignedToTasksAndMilestones() {
        return canBeAssignedToTasksAndMilestones;
    }

    public void setCanBeAssignedToTasksAndMilestones(String canBeAssignedToTasksAndMilestones) {
        this.canBeAssignedToTasksAndMilestones = canBeAssignedToTasksAndMilestones;
    }

    public String getProjectAdministrator() {
        return projectAdministrator;
    }

    public void setProjectAdministrator(String projectAdministrator) {
        this.projectAdministrator = projectAdministrator;
    }

    public String getAddPeopleToProject() {
        return addPeopleToProject;
    }

    public void setAddPeopleToProject(String addPeopleToProject) {
        this.addPeopleToProject = addPeopleToProject;
    }

    public static final class Builder {
        private String viewMessagesAndFiles;
        private String viewTasksAndMilestones;
        private String viewTime;
        private String viewNotebooks;
        private String viewRiskRegister;
        private String viewInvoices;
        private String viewLinks;
        private String addTasks;
        private String addMilestones;
        private String addTaskLists;
        private String addMessages;
        private String addFiles;
        private String addTime;
        private String addNotebooks;
        private String addLinks;
        private String setPrivacy;
        private String canBeAssignedToTasksAndMilestones;
        private String projectAdministrator;
        private String addPeopleToProject;

        private Builder() {
        }

        public Builder viewMessagesAndFiles(String viewMessagesAndFiles) {
            this.viewMessagesAndFiles = viewMessagesAndFiles;
            return this;
        }

        public Builder viewTasksAndMilestones(String viewTasksAndMilestones) {
            this.viewTasksAndMilestones = viewTasksAndMilestones;
            return this;
        }

        public Builder viewTime(String viewTime) {
            this.viewTime = viewTime;
            return this;
        }

        public Builder viewNotebooks(String viewNotebooks) {
            this.viewNotebooks = viewNotebooks;
            return this;
        }

        public Builder viewRiskRegister(String viewRiskRegister) {
            this.viewRiskRegister = viewRiskRegister;
            return this;
        }

        public Builder viewInvoices(String viewInvoices) {
            this.viewInvoices = viewInvoices;
            return this;
        }

        public Builder viewLinks(String viewLinks) {
            this.viewLinks = viewLinks;
            return this;
        }

        public Builder addTasks(String addTasks) {
            this.addTasks = addTasks;
            return this;
        }

        public Builder addMilestones(String addMilestones) {
            this.addMilestones = addMilestones;
            return this;
        }

        public Builder addTaskLists(String addTaskLists) {
            this.addTaskLists = addTaskLists;
            return this;
        }

        public Builder addMessages(String addMessages) {
            this.addMessages = addMessages;
            return this;
        }

        public Builder addFiles(String addFiles) {
            this.addFiles = addFiles;
            return this;
        }

        public Builder addTime(String addTime) {
            this.addTime = addTime;
            return this;
        }

        public Builder addNotebooks(String addNotebooks) {
            this.addNotebooks = addNotebooks;
            return this;
        }

        public Builder addLinks(String addLinks) {
            this.addLinks = addLinks;
            return this;
        }

        public Builder setPrivacy(String setPrivacy) {
            this.setPrivacy = setPrivacy;
            return this;
        }

        public Builder canBeAssignedToTasksAndMilestones(String canBeAssignedToTasksAndMilestones) {
            this.canBeAssignedToTasksAndMilestones = canBeAssignedToTasksAndMilestones;
            return this;
        }

        public Builder projectAdministrator(String projectAdministrator) {
            this.projectAdministrator = projectAdministrator;
            return this;
        }

        public Builder addPeopleToProject(String addPeopleToProject) {
            this.addPeopleToProject = addPeopleToProject;
            return this;
        }

        public Permissions build() {
            return new Permissions(this);
        }
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "viewMessagesAndFiles='" + viewMessagesAndFiles + '\'' +
                ", viewTasksAndMilestones='" + viewTasksAndMilestones + '\'' +
                ", viewTime='" + viewTime + '\'' +
                ", viewNotebooks='" + viewNotebooks + '\'' +
                ", viewRiskRegister='" + viewRiskRegister + '\'' +
                ", viewInvoices='" + viewInvoices + '\'' +
                ", viewLinks='" + viewLinks + '\'' +
                ", addTasks='" + addTasks + '\'' +
                ", addMilestones='" + addMilestones + '\'' +
                ", addTaskLists='" + addTaskLists + '\'' +
                ", addMessages='" + addMessages + '\'' +
                ", addFiles='" + addFiles + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addNotebooks='" + addNotebooks + '\'' +
                ", addLinks='" + addLinks + '\'' +
                ", setPrivacy='" + setPrivacy + '\'' +
                ", canBeAssignedToTasksAndMilestones='" + canBeAssignedToTasksAndMilestones + '\'' +
                ", projectAdministrator='" + projectAdministrator + '\'' +
                ", addPeopleToProject='" + addPeopleToProject + '\'' +
                '}';
    }
}