package com.onetouch.tenant.emanyatta.data.local.dao;

import com.onetouch.tenant.emanyatta.data.local.model.Average;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AverageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAverages(Average average);

    @Query("SELECT * FROM Average")
    Average getAverage();
}
