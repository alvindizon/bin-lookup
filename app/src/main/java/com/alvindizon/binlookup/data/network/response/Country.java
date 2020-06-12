
package com.alvindizon.binlookup.data.network.response;

import com.squareup.moshi.Json;

public class Country {

    @Json(name = "numeric")
    private String numeric;
    @Json(name = "alpha2")
    private String alpha2;
    @Json(name = "name")
    private String name;
    @Json(name = "emoji")
    private String emoji;
    @Json(name = "currency")
    private String currency;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "longitude")
    private Double longitude;

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
