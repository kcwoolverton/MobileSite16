package com.example.textbasedadventuregame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface InventoryEntityDao {
    @Query("SELECT * FROM inventoryEntity")
    List<InventoryEntity> getAll();

    @Query("SELECT * FROM inventoryEntity WHERE uid IN (:inventoryEntityIds)")
    List<InventoryEntity> loadAllByIds(int[] inventoryEntityIds);

    @Insert
    void insertAll(InventoryEntity... inventoryEntities);

    @Delete
    void delete(InventoryEntity inventoryEntities);
}
