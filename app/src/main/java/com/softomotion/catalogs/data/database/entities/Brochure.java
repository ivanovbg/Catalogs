package com.softomotion.catalogs.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "brochure")
public class Brochure {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "brochure_name")
    private String brochure_name;

    @ColumnInfo(name = "brochure_id")
    private Integer brochure_id;

    @ColumnInfo(name="brochure_image")
    private String brochure_image;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBrochure_name() {
        return brochure_name;
    }

    public void setBrochure_name(String brochure_name) {
        this.brochure_name = brochure_name;
    }

    public Integer getBrochure_id() {
        return brochure_id;
    }

    public void setBrochure_id(Integer brochure_id) {
        this.brochure_id = brochure_id;
    }

    public String getBrochure_image() {
        return brochure_image;
    }

    public void setBrochure_image(String brochure_image) {
        this.brochure_image = brochure_image;
    }

    @Override
    public String toString() {
        return "Brochure{" +
                "uid=" + uid +
                ", brochure_name='" + brochure_name + '\'' +
                ", brochure_id=" + brochure_id +
                ", brochure_image='" + brochure_image + '\'' +
                '}';
    }
}
