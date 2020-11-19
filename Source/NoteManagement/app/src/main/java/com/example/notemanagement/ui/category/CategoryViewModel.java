package com.example.notemanagement.ui.category;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notemanagement.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> categoryList;

    public CategoryViewModel(Application application){
        super(application);
        categoryRepository = new CategoryRepository(application);
        categoryList = categoryRepository.getAllCategories();
    }
    LiveData<List<Category>> getAllCategories(){
        return categoryList;
    }

    public  void insert(Category category){
        categoryRepository.insert(category);
    }
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
    public void update(Category category) { categoryRepository.update(category);}
}