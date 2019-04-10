package com.example.textbasedadventuregame;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InventoryEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "shiv")
    public boolean shiv;

    @ColumnInfo(name = "pipe")
    public boolean pipe;

    @ColumnInfo(name = "prison_floor_id_card")
    public boolean prison_floor_id_card;

    @ColumnInfo(name = "medkit")
    public boolean medkit;

    @ColumnInfo(name = "scalpel")
    public boolean scalpel;

    public boolean getShiv() { return this.shiv; }

    public void setShiv(boolean shiv) { this.shiv = shiv; }

    public boolean getPipe() { return this.pipe; }

    public void setPipe(boolean pipe) { this.pipe = pipe; }

    public boolean getPrisonFloorIdCard() { return this.prison_floor_id_card; }

    public void setPrisonFloorIdCard(boolean prison_floor_id_card) { this.prison_floor_id_card = prison_floor_id_card; }

    public boolean getMedkit() { return this.medkit; }

    public void setMedkit(boolean medkit) { this.medkit = medkit; }

    public boolean getScalpel() { return this.scalpel; }

    public void setScalpel(boolean scalpel) { this.scalpel = scalpel; }

}
