package com.nikoyuwono.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("id")
    private String id;
    @SerializedName("can_see_private")
    private boolean canSeePrivate;
    @SerializedName("company_name_url")
    private String companyNameUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("address_one")
    private String addressOne;
    @SerializedName("address_two")
    private String addressTwo;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("zip")
    private String zip;
    @SerializedName("country")
    private String country;
    @SerializedName("website")
    private String website;
    @SerializedName("phone")
    private String phone;
    @SerializedName("fax")
    private String fax;

    private Company(Builder builder) {
        setId(builder.id);
        setCanSeePrivate(builder.canSeePrivate);
        setCompanyNameUrl(builder.companyNameUrl);
        setName(builder.name);
        setAddressOne(builder.addressOne);
        setAddressTwo(builder.addressTwo);
        setCity(builder.city);
        setState(builder.state);
        setZip(builder.zip);
        setCountry(builder.country);
        setWebsite(builder.website);
        setPhone(builder.phone);
        setFax(builder.fax);
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

    public boolean isCanSeePrivate() {
        return canSeePrivate;
    }

    public void setCanSeePrivate(boolean canSeePrivate) {
        this.canSeePrivate = canSeePrivate;
    }

    public String getCompanyNameUrl() {
        return companyNameUrl;
    }

    public void setCompanyNameUrl(String companyNameUrl) {
        this.companyNameUrl = companyNameUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public static final class Builder {
        private String id;
        private boolean canSeePrivate;
        private String companyNameUrl;
        private String name;
        private String addressOne;
        private String addressTwo;
        private String city;
        private String state;
        private String zip;
        private String country;
        private String website;
        private String phone;
        private String fax;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder canSeePrivate(boolean canSeePrivate) {
            this.canSeePrivate = canSeePrivate;
            return this;
        }

        public Builder companyNameUrl(String companyNameUrl) {
            this.companyNameUrl = companyNameUrl;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder addressOne(String addressOne) {
            this.addressOne = addressOne;
            return this;
        }

        public Builder addressTwo(String addressTwo) {
            this.addressTwo = addressTwo;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", canSeePrivate=" + canSeePrivate +
                ", companyNameUrl='" + companyNameUrl + '\'' +
                ", name='" + name + '\'' +
                ", addressOne='" + addressOne + '\'' +
                ", addressTwo='" + addressTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}