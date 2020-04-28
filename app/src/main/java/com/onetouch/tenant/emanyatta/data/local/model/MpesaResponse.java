package com.onetouch.tenant.emanyatta.data.local.model;

import com.google.gson.annotations.SerializedName;

public class MpesaResponse {

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("message")
    private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
