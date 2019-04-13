package com.example.textbasedadventuregame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StatusEntityDao {
    @Query("SELECT * FROM statusEntity")
    List<StatusEntity> getAll();

    @Query("SELECT * FROM statusEntity WHERE uid IN (:statusEntityIds)")
    List<StatusEntity> loadAllByIds(int[] statusEntityIds);

    @Update
    void update(StatusEntity statusEntity);

    @Insert
    void insert(StatusEntity statusEntity);

    @Insert
    void insertAll(StatusEntity... statusEntities);

    @Delete
    void delete(StatusEntity statusEntity);
}
