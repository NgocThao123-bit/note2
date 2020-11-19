package com.example.notemanagement;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notemanagement.dao.CategoryDAO;
import com.example.notemanagement.ui.category.Category;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();

}
