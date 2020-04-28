package com.onetouch.tenant.emanyatta.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Complain")
public class Complain {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int complainId;
    @ColumnInfo(name = "complain_name")
    private String complainName;
    @ColumnInfo(name = "house_number")
    private String houseNumber;
    @ColumnInfo(name = "date_recorded")
    private String dateRecorded;
    @ColumnInfo(name = "date_resolved")
    private String dateResolved;
    @ColumnInfo(name = "complain_status")
    private String complainStatus;
    @ColumnInfo(name = "complain_description")
    private String complainDescription;
    @ColumnInfo(name = "apartment_name")
    private String apartmentName;

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public String getComplainName() {
        return complainName;
    }

    public void setComplainName(String complainName) {
        this.complainName = complainName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(String dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public String getComplainDescription() {
        return complainDescription;
    }

    public void setComplainDescription(String complainDescription) {
        this.complainDescription = complainDescription;
    }
}
