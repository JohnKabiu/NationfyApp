package com.onetouch.tenant.emanyatta.data.local.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bill")
public class Bill {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("idbill")
    @NonNull
    private String billId;

    @SerializedName("paymentnotes")
    @ColumnInfo(name = "bill_name")
    private String billName;

    @SerializedName("name")
    @ColumnInfo(name = "account_number")
    private String accountNo;

    //    @SerializedName("HouseName")
    @ColumnInfo(name = "house_number")
    private String houseNo;

    @SerializedName("rentamount")
    @ColumnInfo(name = "amount_value")
    private String amountValue;

    @SerializedName("rentDuedate")
    @ColumnInfo(name = "due_date")
    private String dueDate;

    @SerializedName("buildingname")
    @ColumnInfo(name = "apartment_name")
    private String apartmentName;

    @SerializedName("payAccount")
    @ColumnInfo(name = "pay_account")
    private String payAccount;

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = amountValue;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }
}
