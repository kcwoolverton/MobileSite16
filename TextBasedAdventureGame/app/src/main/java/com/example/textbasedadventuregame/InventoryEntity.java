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

    @ColumnInfo(name = "gun")
    public boolean gun;

    @ColumnInfo(name = "ammo")
    public int ammo;

    @ColumnInfo(name = "armor")
    public boolean armor;

    @ColumnInfo(name = "foundation_employee_id_card")
    public boolean foundation_employee_id_card;

    @ColumnInfo(name = "cheese")
    public boolean cheese;

    @ColumnInfo(name = "food_supplies")
    public boolean food_supplies;

    @ColumnInfo(name = "obsidian_knife")
    public boolean obsidian_knife;

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

    public boolean getGun() { return this.gun; }

    public void setGun(boolean gun) { this.gun = gun; }

    public int getAmmo() { return this.ammo; }

    public void setAmmo(int ammo) { this.ammo = ammo; }

    public boolean getArmor() { return this.armor; }

    public void setArmor(boolean armor) { this.armor = armor; }

    public boolean getFoundationEmployeeIdCard() { return this.foundation_employee_id_card; }

    public void setFoundationEmployeeIdCard(boolean foundation_employee_id_card) { this.foundation_employee_id_card = foundation_employee_id_card; }

    public boolean getCheese() { return this.cheese; }

    public void setCheese(boolean cheese) { this.cheese = cheese; }

    public boolean getFoodSupplies() { return this.food_supplies; }

    public void setFoodSupplies(boolean food_supplies) { this.food_supplies = food_supplies; }

    public boolean getObsidianKnife() { return this.obsidian_knife; }

    public void setObsidianKnife(boolean obsidian_knife) { this.obsidian_knife = obsidian_knife; }
}
