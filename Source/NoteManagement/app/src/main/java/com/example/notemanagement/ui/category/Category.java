package com.example.notemanagement.ui.category;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nam_cate")
    private String nameCate;

    @ColumnInfo(name = "date_cate")
    private String dateCate;

    public Category(@NonNull String nameCate) {
        this.nameCate = nameCate;
        this.dateCate = (Calendar.getInstance().getTime()).toString();
    }

    @NonNull
    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(@NonNull String nameCate) {
        this.nameCate = nameCate;
    }

    public String getDateCate() {
        return dateCate;
    }

    public void setDateCate(String dateCate) {
        this.dateCate = dateCate;
    }
}
