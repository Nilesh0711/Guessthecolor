package com.example.guessthecolor_v1.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Colors.class}, version = 1, exportSchema = true)
public abstract class ColorsDatabase extends RoomDatabase {

    private static ColorsDatabase instance;

    public static synchronized ColorsDatabase getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ColorsDatabase.class, "colors")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/colors.db")
                    .build();
        }
        return instance;
    }

    public abstract ColorsDao userDao();
}
