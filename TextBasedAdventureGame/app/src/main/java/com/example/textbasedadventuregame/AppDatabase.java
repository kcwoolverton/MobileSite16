package com.example.textbasedadventuregame;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlayerEntity.class, InventoryEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase DBINSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (DBINSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (DBINSTANCE == null) {
                    // Create database here
                    DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return DBINSTANCE;
    }

    public abstract PlayerEntityDao playerEntityDao();
    public abstract InventoryEntityDao inventoryEntityDao();
}
