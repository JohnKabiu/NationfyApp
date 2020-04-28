package com.onetouch.tenant.emanyatta.data.local.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "House")
public class House {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int houseId;

    @SerializedName("HouseName")
    @ColumnInfo(name = "house_name")
    private String houseName;

    @SerializedName("type")
    @ColumnInfo(name = "house_location")
    private String houseLocation;

    @SerializedName("rentamount")
    @ColumnInfo(name = "house_cost")
    private String houseCost;

    @SerializedName("imagearray")
    @ColumnInfo(name = "imagearray")
    private String imageUrl;

    @SerializedName("deposit")
    @ColumnInfo(name = "house_description")
    private String houseDescription;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public String getHouseCost() {
        return houseCost;
    }

    public void setHouseCost(String houseCost) {
        this.houseCost = houseCost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null) {
            this.imageUrl = "";
        }
        this.imageUrl = imageUrl;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }
}
