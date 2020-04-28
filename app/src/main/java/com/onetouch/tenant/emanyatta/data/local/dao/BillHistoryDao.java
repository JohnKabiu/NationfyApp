package com.onetouch.tenant.emanyatta.data.local.dao;

import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BillHistoryDao {
    @Query("SELECT * FROM BillHistory")
    LiveData<List<BillHistory>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBillHistories(List<BillHistory> billHistories);

}
