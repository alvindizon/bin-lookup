
package com.alvindizon.binlookup.data.network.response;

import com.squareup.moshi.Json;

public class Bank {

    @Json(name = "name")
    private String name;
    @Json(name = "url")
    private String url;
    @Json(name = "phone")
    private String phone;
    @Json(name = "city")
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
