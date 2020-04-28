package com.onetouch.tenant.emanyatta.data.local.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Average")
public class Average {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("complain")
    private String complain;

    @SerializedName("pending")
    private String pending;

    @SerializedName("paid")
    private String paid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
