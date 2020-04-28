package com.onetouch.tenant.emanyatta.data.local.dao;


import com.onetouch.tenant.emanyatta.data.local.model.Complain;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ComplainDao {
    @Query("SELECT * FROM Complain")
    LiveData<List<Complain>> getAll();
}
