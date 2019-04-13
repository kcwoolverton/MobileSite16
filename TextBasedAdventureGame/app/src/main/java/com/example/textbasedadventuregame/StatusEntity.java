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
}
