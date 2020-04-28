package com.onetouch.tenant.emanyatta.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.onetouch.tenant.emanyatta.data.local.dao.AverageDao;
import com.onetouch.tenant.emanyatta.data.local.dao.BillCategoryDao;
import com.onetouch.tenant.emanyatta.data.local.dao.BillDao;
import com.onetouch.tenant.emanyatta.data.local.dao.BillHistoryDao;
import com.onetouch.tenant.emanyatta.data.local.dao.ComplainDao;
import com.onetouch.tenant.emanyatta.data.local.dao.HouseDao;
import com.onetouch.tenant.emanyatta.data.local.dao.UserDao;
import com.onetouch.tenant.emanyatta.data.local.model.Average;
import com.onetouch.tenant.emanyatta.data.local.model.Bill;
import com.onetouch.tenant.emanyatta.data.local.model.BillCategory;
import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;
import com.onetouch.tenant.emanyatta.data.local.model.Complain;
import com.onetouch.tenant.emanyatta.data.local.model.House;
import com.onetouch.tenant.emanyatta.data.local.model.User;

@Database(entities = {Bill.class, Complain.class, House.class, BillCategory.class, BillHistory.class, User.class, Average.class}, version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "emanyatta_db")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract BillDao billDao();

    public abstract ComplainDao complainDao();

    public abstract HouseDao houseDao();

    public abstract BillCategoryDao billCategoryDao();

    public abstract BillHistoryDao billHistoryDao();

    public abstract UserDao userDao();

    public abstract AverageDao averageDao();

}