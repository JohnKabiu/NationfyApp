package com.onetouch.tenant.emanyatta.data.local.dao;

import com.onetouch.tenant.emanyatta.data.local.model.Bill;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BillDao {
    @Query("SELECT * FROM Bill")
    LiveData<List<Bill>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBills(List<Bill> bills);
}
