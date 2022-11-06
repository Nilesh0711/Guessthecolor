package com.example.guessthecolor_v1.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ColorsDao {

    @Query("SELECT * FROM colors")
    List<Colors> getAll();

    @Query("SELECT * FROM colors WHERE uid in (:userIds)")
    List<Colors> loadAllByIds(int[] userIds);

    @Delete
    void delete(Colors color);

}
