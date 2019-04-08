package com.example.textbasedadventuregame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerEntityDao {
    @Query("SELECT * FROM playerEntity")
    List<PlayerEntity> getAll();

    @Query("SELECT * FROM playerEntity WHERE uid IN (:playerEntityIds)")
    List<PlayerEntity> loadAllByIds(int[] playerEntityIds);

    @Query("SELECT * FROM playerEntity WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    PlayerEntity findByName(String first, String last);

    @Insert
    void insertAll(PlayerEntity... playerEntities);

    @Update
    void update(PlayerEntity playerEntity);

    @Insert
    void insert(PlayerEntity playerEntity);

    @Delete
    void delete(PlayerEntity playerEntity);
}
