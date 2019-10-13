
package com.alvindizon.binlookup.data.network.response;

import com.squareup.moshi.Json;

public class Number {

    @Json(name = "length")
    private Integer length;
    @Json(name = "luhn")
    private Boolean luhn;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getLuhn() {
        return luhn;
    }

    public void setLuhn(Boolean luhn) {
        this.luhn = luhn;
    }

}
