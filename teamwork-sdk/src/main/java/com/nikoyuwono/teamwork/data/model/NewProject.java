package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class NewProject {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("companyId")
    private String companyId;
    @SerializedName("newCompany")
    private String newCompany;
    @SerializedName("category-id")
    private String categoryId;
    @SerializedName("start-date")
    private String startDate;
    @SerializedName("end-date")
    private String endDate;
    @SerializedName("use-tasks")
    private String useTasks;
    @SerializedName("use-milestones")
    private String useMilestones;
    @SerializedName("use-messages")
    private String useMessages;
    @SerializedName("use-files")
    private String useFiles;
    @SerializedName("use-time")
    private String useTime;
    @SerializedName("use-notebook")
    private String useNotebook;
    @SerializedName("use-riskregister")
    private String useRiskregister;
    @SerializedName("use-links")
    private String useLinks;
    @SerializedName("use-billing")
    private String useBilling;
    @SerializedName("start-page")
    private String startPage;
    @SerializedName("harvest-timers-enabled")
    private String harvestTimersEnabled;
    @SerializedName("defaultPrivacy")
    private String defaultPrivacy;

    private NewProject(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setCompanyId(builder.companyId);
        setNewCompany(builder.newCompany);
        setCategoryId(builder.categoryId);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
        setUseTasks(builder.useTasks);
        setUseMilestones(builder.useMilestones);
        setUseMessages(builder.useMessages);
        setUseFiles(builder.useFiles);
        setUseTime(builder.useTime);
        setUseNotebook(builder.useNotebook);
        setUseRiskregister(builder.useRiskregister);
        setUseLinks(builder.useLinks);
        setUseBilling(builder.useBilling);
        setStartPage(builder.startPage);
        setHarvestTimersEnabled(builder.harvestTimersEnabled);
        setDefaultPrivacy(builder.defaultPrivacy);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNewCompany() {
        return newCompany;
    }

    public void setNewCompany(String newCompany) {
        this.newCompany = newCompany;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getUseTasks() {
        return useTasks;
    }

    public void setUseTasks(String useTasks) {
        this.useTasks = useTasks;
    }

    public String getUseMilestones() {
        return useMilestones;
    }

    public void setUseMilestones(String useMilestones) {
        this.useMilestones = useMilestones;
    }

    public String getUseMessages() {
        return useMessages;
    }

    public void setUseMessages(String useMessages) {
        this.useMessages = useMessages;
    }

    public String getUseFiles() {
        return useFiles;
    }

    public void setUseFiles(String useFiles) {
        this.useFiles = useFiles;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getUseNotebook() {
        return useNotebook;
    }

    public void setUseNotebook(String useNotebook) {
        this.useNotebook = useNotebook;
    }

    public String getUseRiskregister() {
        return useRiskregister;
    }

    public void setUseRiskregister(String useRiskregister) {
        this.useRiskregister = useRiskregister;
    }

    public String getUseLinks() {
        return useLinks;
    }

    public void setUseLinks(String useLinks) {
        this.useLinks = useLinks;
    }

    public String getUseBilling() {
        return useBilling;
    }

    public void setUseBilling(String useBilling) {
        this.useBilling = useBilling;
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

    public String getDefaultPrivacy() {
        return defaultPrivacy;
    }

    public void setDefaultPrivacy(String defaultPrivacy) {
        this.defaultPrivacy = defaultPrivacy;
    }

    public static final class Builder {
        private String name;
        private String description;
        private String companyId;
        private String newCompany;
        private String categoryId;
        private String startDate;
        private String endDate;
        private String useTasks;
        private String useMilestones;
        private String useMessages;
        private String useFiles;
        private String useTime;
        private String useNotebook;
        private String useRiskregister;
        private String useLinks;
        private String useBilling;
        private String startPage;
        private String harvestTimersEnabled;
        private String defaultPrivacy;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder newCompany(String newCompany) {
            this.newCompany = newCompany;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
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

        public Builder useTasks(String useTasks) {
            this.useTasks = useTasks;
            return this;
        }

        public Builder useMilestones(String useMilestones) {
            this.useMilestones = useMilestones;
            return this;
        }

        public Builder useMessages(String useMessages) {
            this.useMessages = useMessages;
            return this;
        }

        public Builder useFiles(String useFiles) {
            this.useFiles = useFiles;
            return this;
        }

        public Builder useTime(String useTime) {
            this.useTime = useTime;
            return this;
        }

        public Builder useNotebook(String useNotebook) {
            this.useNotebook = useNotebook;
            return this;
        }

        public Builder useRiskregister(String useRiskregister) {
            this.useRiskregister = useRiskregister;
            return this;
        }

        public Builder useLinks(String useLinks) {
            this.useLinks = useLinks;
            return this;
        }

        public Builder useBilling(String useBilling) {
            this.useBilling = useBilling;
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

        public Builder defaultPrivacy(String defaultPrivacy) {
            this.defaultPrivacy = defaultPrivacy;
            return this;
        }

        public NewProject build() {
            return new NewProject(this);
        }
    }

    @Override
    public String toString() {
        return "NewProject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", companyId='" + companyId + '\'' +
                ", newCompany='" + newCompany + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", useTasks='" + useTasks + '\'' +
                ", useMilestones='" + useMilestones + '\'' +
                ", useMessages='" + useMessages + '\'' +
                ", useFiles='" + useFiles + '\'' +
                ", useTime='" + useTime + '\'' +
                ", useNotebook='" + useNotebook + '\'' +
                ", useRiskregister='" + useRiskregister + '\'' +
                ", useLinks='" + useLinks + '\'' +
                ", useBilling='" + useBilling + '\'' +
                ", startPage='" + startPage + '\'' +
                ", harvestTimersEnabled='" + harvestTimersEnabled + '\'' +
                ", defaultPrivacy='" + defaultPrivacy + '\'' +
                '}';
    }
}
