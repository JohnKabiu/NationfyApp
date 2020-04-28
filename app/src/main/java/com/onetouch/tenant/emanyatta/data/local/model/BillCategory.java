package com.onetouch.tenant.emanyatta.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BillCategory")
public class BillCategory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int categoryId;
    @ColumnInfo(name = "name")
    private String categoryName;
    @ColumnInfo(name = "image")
    private int categoryPicture;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(int categoryPicture) {
        this.categoryPicture = categoryPicture;
    }
}
