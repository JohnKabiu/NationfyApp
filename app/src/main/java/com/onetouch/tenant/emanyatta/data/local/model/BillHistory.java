package com.onetouch.tenant.emanyatta.data.local.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BillHistory")
public class BillHistory {

    //    @SerializedName("")
    @ColumnInfo(name = "id")
    @NonNull
    private long billId;

    @SerializedName("HouseName")
    @ColumnInfo(name = "house_name")
    private String houseName;

    @SerializedName("paid_amount")
    @ColumnInfo(name = "paid_amount")
    private String paidAmount;

    @SerializedName("current_balance")
    @ColumnInfo(name = "current_balance")
    private String currentBalance;

    @SerializedName("paymentnotes")
    @ColumnInfo(name = "payment_name")
    private String paymentName;

    @SerializedName("creationdate")
    @ColumnInfo(name = "date_paid")
    private String datePaid;

    @PrimaryKey
    @SerializedName("rentpaymentid")
    @ColumnInfo(name = "rent_payment_id")
    @NonNull private String rentPaymentId;

    @SerializedName("tenant_id")
    @ColumnInfo(name = "tenant_id")
    private String tenantId;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public String getRentPaymentId() {
        return rentPaymentId;
    }

    public void setRentPaymentId(String rentPaymentId) {
        this.rentPaymentId = rentPaymentId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
