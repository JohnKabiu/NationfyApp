package com.onetouch.tenant.emanyatta.data.local.dao;

import com.onetouch.tenant.emanyatta.data.local.model.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM User")
    User getUser();

    @Query("SELECT userId FROM User WHERE mobile =:mobile")
    int getUserId(String mobile);
}
