package com.example.textbasedadventuregame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class TheGame extends AppCompatActivity {

    @Database(entities = {PlayerEntity.class, InventoryEntity.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract PlayerEntityDao playerDao();
        public abstract InventoryEntityDao inventoryDao();
    }

    public interface Player {
        String firstName = "";
        String lastName = "";
        int backgroundId = 0;
    }

    @Entity
    public class PlayerEntity implements Player {
        @PrimaryKey
        public int uid;

        @ColumnInfo(name = "first_name")
        public String firstName;

        @ColumnInfo(name = "last_name")
        public String lastName;

        @ColumnInfo(name = "background")
        public int backgroundId;
    }

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

        @Insert
        void insert(PlayerEntity playerEntities);

        @Delete
        void delete(PlayerEntity playerEntities);
    }

    public interface Inventory {
        char object1 = 0;
        char object2 = 0;
    }

    @Entity
    public class InventoryEntity implements Inventory {
        @PrimaryKey
        public int uid;
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_game);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
    }
}
