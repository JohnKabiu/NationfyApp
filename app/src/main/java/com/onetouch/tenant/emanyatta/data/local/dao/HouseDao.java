package com.onetouch.tenant.emanyatta.data.local.dao;

import com.onetouch.tenant.emanyatta.data.local.model.House;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface HouseDao {
    @Query("SELECT * FROM House")
    LiveData<List<House>> getAll();

    @Query("SELECT * FROM House WHERE id = :houseId")
    House getHouse(int houseId);

    @Insert
    void insertHouse(List<House> houses);
}
