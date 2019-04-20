package com.example.textbasedadventuregame;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class StatusEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "took_cell_sink_pipe")
    public boolean took_cell_sink_pipe;

    @ColumnInfo(name = "broke_cell_wall")
    public boolean broke_cell_wall;

    @ColumnInfo(name = "took_prison_medbay_scalpel")
    public boolean took_prison_medbay_scalpel;

    @ColumnInfo(name = "took_prison_medkit")
    public boolean took_prison_medkit;

    @ColumnInfo(name = "took_prison_id_card")
    public boolean took_prison_id_card;

    @ColumnInfo(name = "took_cheese")
    public boolean took_cheese;

    @ColumnInfo(name = "fed_josie")
    public boolean fed_josie;

    @ColumnInfo(name = "need_supplies")
    public boolean need_supplies;

    @ColumnInfo(name = "took_supplies")
    public boolean took_supplies;

    @ColumnInfo(name = "death_by_ocean")
    public boolean death_by_ocean;

    @ColumnInfo(name = "know_site_director")
    public boolean know_site_director;

    @ColumnInfo(name = "found_site_director")
    public boolean found_site_director;

    @ColumnInfo(name = "knifed_site_director")
    public boolean knifed_site_director;

    @ColumnInfo(name = "took_gun")
    public boolean took_gun;

    @ColumnInfo(name = "armor")
    public boolean took_armor;

    @ColumnInfo(name = "knows_bunny")
    public boolean knows_bunny;

    public boolean getTookCellSinkPipe() { return this.took_cell_sink_pipe; }

    public void setTookCellSinkPipe(boolean took_cell_sink_pipe) { this.took_cell_sink_pipe = took_cell_sink_pipe; }

    public boolean getBrokeCellWall() { return this.broke_cell_wall; }

    public void setBrokeCellWall(boolean broke_cell_wall) { this.broke_cell_wall = broke_cell_wall; }

    public boolean getTookPrisonMedbayScalpel() { return this.took_prison_medbay_scalpel; }

    public void setTookPrisonMedbayScalpel(boolean took_prison_medbay_scalpel) { this.took_prison_medbay_scalpel = took_prison_medbay_scalpel; }

    public boolean getTookPrisonMedkit() { return this.took_prison_medkit; }

    public void setTookPrisonMedkit(boolean took_prison_medkit) { this.took_prison_medkit = took_prison_medkit; }

    public boolean getTookPrisonIdCard() { return this.took_prison_id_card; }

    public void setTookPrisonIdCard(boolean took_prison_id_card) { this.took_prison_id_card = took_prison_id_card; }

    public boolean getTookCheese() { return this.took_cheese; }

    public void setTookCheese(boolean took_cheese) { this.took_prison_id_card = took_cheese; }

    public boolean getFedJosie() { return this.fed_josie; }

    public void setFedJosie(boolean fed_josie) { this.fed_josie = fed_josie; }

    public boolean getNeedSupplies() { return this.need_supplies; }

    public void setNeedSupplies(boolean need_supplies) { this.need_supplies = need_supplies; }

    public boolean getTookSupplies() { return this.took_supplies; }

    public void setTookSupplies(boolean took_supplies) { this.took_supplies = took_supplies; }

    public boolean getDeathByOcean() { return this.death_by_ocean; }

    public void setDeathByOcean(boolean death_by_ocean) { this.death_by_ocean = death_by_ocean; }

    public boolean getKnowSiteDirector() { return this.know_site_director; }

    public void setKnowSiteDirector(boolean know_site_director) { this.know_site_director = know_site_director; }

    public boolean getFoundSiteDirector() { return this.found_site_director; }

    public void setFoundSiteDirector(boolean found_site_director) { this.found_site_director = found_site_director; }

    public boolean getKnifedSiteDirector() { return this.knifed_site_director; }

    public void setKnifedSiteDirector(boolean knifed_site_director) { this.knifed_site_director = knifed_site_director; }

    public boolean getTookGun() { return this.took_gun; }

    public void setTookGun(boolean took_gun) { this.took_gun = took_gun; }

    public boolean getTookArmor() { return this.took_armor; }

    public void setTookArmor(boolean took_armor) { this.took_armor = took_armor; }

    public boolean getKnowsBunny() { return this.knows_bunny; }

    public void setKnowsBunny(boolean knows_bunny) { this.knows_bunny = knows_bunny; }


}
