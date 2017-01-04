package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("requirehttps")
    private boolean requireHttps;
    @SerializedName("time-tracking-enabled")
    private boolean timeTrackingEnabled;
    @SerializedName("name")
    private String name;
    @SerializedName("datesignedup")
    private String dateSignedUp;
    @SerializedName("companyname")
    private String companyName;
    @SerializedName("ssl-enabled")
    private boolean sslEnabled;
    @SerializedName("created-at")
    private String createdAt;
    @SerializedName("cacheUUID")
    private String cacheUUID;
    @SerializedName("account-holder-id")
    private String accountHolderId;
    @SerializedName("logo")
    private String logo;
    @SerializedName("id")
    private String id;
    @SerializedName("URL")
    private String url;
    @SerializedName("email-notification-enabled")
    private boolean emailNotificationEnabled;
    @SerializedName("companyid")
    private String companyId;
    @SerializedName("lang")
    private String lang;
    @SerializedName("code")
    private String code;

    private Account(Builder builder) {
        setRequireHttps(builder.requireHttps);
        setTimeTrackingEnabled(builder.timeTrackingEnabled);
        setName(builder.name);
        setDateSignedUp(builder.dateSignedUp);
        setCompanyName(builder.companyName);
        setSslEnabled(builder.sslEnabled);
        setCreatedAt(builder.createdAt);
        setCacheUUID(builder.cacheUUID);
        setAccountHolderId(builder.accountHolderId);
        setLogo(builder.logo);
        setId(builder.id);
        setUrl(builder.url);
        setEmailNotificationEnabled(builder.emailNotificationEnabled);
        setCompanyId(builder.companyId);
        setLang(builder.lang);
        setCode(builder.code);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isRequireHttps() {
        return requireHttps;
    }

    public void setRequireHttps(boolean requireHttps) {
        this.requireHttps = requireHttps;
    }

    public boolean isTimeTrackingEnabled() {
        return timeTrackingEnabled;
    }

    public void setTimeTrackingEnabled(boolean timeTrackingEnabled) {
        this.timeTrackingEnabled = timeTrackingEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateSignedUp() {
        return dateSignedUp;
    }

    public void setDateSignedUp(String dateSignedUp) {
        this.dateSignedUp = dateSignedUp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCacheUUID() {
        return cacheUUID;
    }

    public void setCacheUUID(String cacheUUID) {
        this.cacheUUID = cacheUUID;
    }

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEmailNotificationEnabled() {
        return emailNotificationEnabled;
    }

    public void setEmailNotificationEnabled(boolean emailNotificationEnabled) {
        this.emailNotificationEnabled = emailNotificationEnabled;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static final class Builder {
        private boolean requireHttps;
        private boolean timeTrackingEnabled;
        private String name;
        private String dateSignedUp;
        private String companyName;
        private boolean sslEnabled;
        private String createdAt;
        private String cacheUUID;
        private String accountHolderId;
        private String logo;
        private String id;
        private String url;
        private boolean emailNotificationEnabled;
        private String companyId;
        private String lang;
        private String code;

        private Builder() {
        }

        public Builder requireHttps(boolean requireHttps) {
            this.requireHttps = requireHttps;
            return this;
        }

        public Builder timeTrackingEnabled(boolean timeTrackingEnabled) {
            this.timeTrackingEnabled = timeTrackingEnabled;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dateSignedUp(String dateSignedUp) {
            this.dateSignedUp = dateSignedUp;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder sslEnabled(boolean sslEnabled) {
            this.sslEnabled = sslEnabled;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder cacheUUID(String cacheUUID) {
            this.cacheUUID = cacheUUID;
            return this;
        }

        public Builder accountHolderId(String accountHolderId) {
            this.accountHolderId = accountHolderId;
            return this;
        }

        public Builder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder emailNotificationEnabled(boolean emailNotificationEnabled) {
            this.emailNotificationEnabled = emailNotificationEnabled;
            return this;
        }

        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "requireHttps=" + requireHttps +
                ", timeTrackingEnabled=" + timeTrackingEnabled +
                ", name='" + name + '\'' +
                ", dateSignedUp='" + dateSignedUp + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sslEnabled=" + sslEnabled +
                ", createdAt='" + createdAt + '\'' +
                ", cacheUUID='" + cacheUUID + '\'' +
                ", accountHolderId='" + accountHolderId + '\'' +
                ", logo='" + logo + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", emailNotificationEnabled=" + emailNotificationEnabled +
                ", companyId='" + companyId + '\'' +
                ", lang='" + lang + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}