package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("id")
    private String id;
    @SerializedName("user-type")
    private String userType;
    @SerializedName("first-name")
    private String firstName;
    @SerializedName("last-name")
    private String lastName;
    @SerializedName("title")
    private String title;
    @SerializedName("email-address")
    private String emailAddress;
    @SerializedName("im-handle")
    private String imHandle;
    @SerializedName("im-service")
    private String imService;
    @SerializedName("notes")
    private String notes;
    @SerializedName("phone-number-office")
    private String phoneNumberOffice;
    @SerializedName("phone-number-office-ext")
    private String phoneNumberOfficeExt;
    @SerializedName("phone-number-mobile")
    private String phoneNumberMobile;
    @SerializedName("phone-number-home")
    private String phoneNumberHome;
    @SerializedName("phone-number-fax")
    private String phoneNumberFax;
    @SerializedName("last-login")
    private String lastLogin;
    @SerializedName("company-id")
    private String companyId;
    @SerializedName("company-name")
    private String companyName;
    @SerializedName("in-owner-company")
    private String inOwnerCompany;
    @SerializedName("created-at")
    private String createdAt;
    @SerializedName("last-changed-on")
    private String lastChangedOn;
    @SerializedName("avatar-url")
    private String avatarUrl;
    @SerializedName("user-name")
    private String userName;
    @SerializedName("deleted")
    private boolean deleted;
    @SerializedName("has-access-to-new-projects")
    private boolean hasAccessToNewProjects;
    @SerializedName("administrator")
    private boolean administrator;
    @SerializedName("permissions")
    private Permissions permissions;

    private Person(Builder builder) {
        setId(builder.id);
        setUserType(builder.userType);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setTitle(builder.title);
        setEmailAddress(builder.emailAddress);
        setImHandle(builder.imHandle);
        setImService(builder.imService);
        setNotes(builder.notes);
        setPhoneNumberOffice(builder.phoneNumberOffice);
        setPhoneNumberOfficeExt(builder.phoneNumberOfficeExt);
        setPhoneNumberMobile(builder.phoneNumberMobile);
        setPhoneNumberHome(builder.phoneNumberHome);
        setPhoneNumberFax(builder.phoneNumberFax);
        setLastLogin(builder.lastLogin);
        setCompanyId(builder.companyId);
        setCompanyName(builder.companyName);
        setInOwnerCompany(builder.inOwnerCompany);
        setCreatedAt(builder.createdAt);
        setLastChangedOn(builder.lastChangedOn);
        setAvatarUrl(builder.avatarUrl);
        setUserName(builder.userName);
        setDeleted(builder.deleted);
        setHasAccessToNewProjects(builder.hasAccessToNewProjects);
        setAdministrator(builder.administrator);
        setPermissions(builder.permissions);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    static class Permissions {

        @SerializedName("can-manage-people")
        private boolean canManagePeople;
        @SerializedName("can-add-projects")
        private boolean canAddProjects;

        public boolean isCanManagePeople() {
            return canManagePeople;
        }

        public void setCanManagePeople(boolean canManagePeople) {
            this.canManagePeople = canManagePeople;
        }

        public boolean isCanAddProjects() {
            return canAddProjects;
        }

        public void setCanAddProjects(boolean canAddProjects) {
            this.canAddProjects = canAddProjects;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getImHandle() {
        return imHandle;
    }

    public void setImHandle(String imHandle) {
        this.imHandle = imHandle;
    }

    public String getImService() {
        return imService;
    }

    public void setImService(String imService) {
        this.imService = imService;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneNumberOffice() {
        return phoneNumberOffice;
    }

    public void setPhoneNumberOffice(String phoneNumberOffice) {
        this.phoneNumberOffice = phoneNumberOffice;
    }

    public String getPhoneNumberOfficeExt() {
        return phoneNumberOfficeExt;
    }

    public void setPhoneNumberOfficeExt(String phoneNumberOfficeExt) {
        this.phoneNumberOfficeExt = phoneNumberOfficeExt;
    }

    public String getPhoneNumberMobile() {
        return phoneNumberMobile;
    }

    public void setPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
    }

    public String getPhoneNumberHome() {
        return phoneNumberHome;
    }

    public void setPhoneNumberHome(String phoneNumberHome) {
        this.phoneNumberHome = phoneNumberHome;
    }

    public String getPhoneNumberFax() {
        return phoneNumberFax;
    }

    public void setPhoneNumberFax(String phoneNumberFax) {
        this.phoneNumberFax = phoneNumberFax;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInOwnerCompany() {
        return inOwnerCompany;
    }

    public void setInOwnerCompany(String inOwnerCompany) {
        this.inOwnerCompany = inOwnerCompany;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastChangedOn() {
        return lastChangedOn;
    }

    public void setLastChangedOn(String lastChangedOn) {
        this.lastChangedOn = lastChangedOn;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isHasAccessToNewProjects() {
        return hasAccessToNewProjects;
    }

    public void setHasAccessToNewProjects(boolean hasAccessToNewProjects) {
        this.hasAccessToNewProjects = hasAccessToNewProjects;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static final class Builder {
        private String id;
        private String userType;
        private String firstName;
        private String lastName;
        private String title;
        private String emailAddress;
        private String imHandle;
        private String imService;
        private String notes;
        private String phoneNumberOffice;
        private String phoneNumberOfficeExt;
        private String phoneNumberMobile;
        private String phoneNumberHome;
        private String phoneNumberFax;
        private String lastLogin;
        private String companyId;
        private String companyName;
        private String inOwnerCompany;
        private String createdAt;
        private String lastChangedOn;
        private String avatarUrl;
        private String userName;
        private boolean deleted;
        private boolean hasAccessToNewProjects;
        private boolean administrator;
        private Permissions permissions;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder userType(String userType) {
            this.userType = userType;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder imHandle(String imHandle) {
            this.imHandle = imHandle;
            return this;
        }

        public Builder imService(String imService) {
            this.imService = imService;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder phoneNumberOffice(String phoneNumberOffice) {
            this.phoneNumberOffice = phoneNumberOffice;
            return this;
        }

        public Builder phoneNumberOfficeExt(String phoneNumberOfficeExt) {
            this.phoneNumberOfficeExt = phoneNumberOfficeExt;
            return this;
        }

        public Builder phoneNumberMobile(String phoneNumberMobile) {
            this.phoneNumberMobile = phoneNumberMobile;
            return this;
        }

        public Builder phoneNumberHome(String phoneNumberHome) {
            this.phoneNumberHome = phoneNumberHome;
            return this;
        }

        public Builder phoneNumberFax(String phoneNumberFax) {
            this.phoneNumberFax = phoneNumberFax;
            return this;
        }

        public Builder lastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder inOwnerCompany(String inOwnerCompany) {
            this.inOwnerCompany = inOwnerCompany;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastChangedOn(String lastChangedOn) {
            this.lastChangedOn = lastChangedOn;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder hasAccessToNewProjects(boolean hasAccessToNewProjects) {
            this.hasAccessToNewProjects = hasAccessToNewProjects;
            return this;
        }

        public Builder administrator(boolean administrator) {
            this.administrator = administrator;
            return this;
        }

        public Builder permissions(Permissions permissions) {
            this.permissions = permissions;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}