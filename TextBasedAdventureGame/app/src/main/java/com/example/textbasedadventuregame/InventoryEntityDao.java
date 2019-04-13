package com.example.textbasedadventuregame;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface InventoryEntityDao {
    @Query("SELECT * FROM inventoryEntity")
    List<InventoryEntity> getAll();

    @Query("SELECT * FROM inventoryEntity WHERE uid IN (:inventoryEntityIds)")
    List<InventoryEntity> loadAllByIds(int[] inventoryEntityIds);

    @Update
    void update(InventoryEntity inventoryEntity);

    @Insert
    void insert(InventoryEntity inventoryEntity);

    @Insert
    void insertAll(InventoryEntity... inventoryEntities);

    @Delete
    void delete(InventoryEntity inventoryEntity);


}
