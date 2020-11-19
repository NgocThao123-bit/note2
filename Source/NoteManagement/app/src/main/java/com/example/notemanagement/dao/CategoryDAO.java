package com.example.notemanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.ui.category.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("select * from Category")
    LiveData<List<Category>> getAllCategories();

    @Query("select * from Category where nam_cate in (:name)")
    Category loadCategoryByName(String name);

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
    @Query("DELETE FROM category")
    void deleteAllCate();

}
