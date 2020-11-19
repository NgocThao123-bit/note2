package com.example.notemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.notemanagement.dao.CategoryDAO;
import com.example.notemanagement.database.CategoryDatabase;
import com.example.notemanagement.ui.category.Category;

import java.util.List;

public class CategoryRepository {
    private CategoryDAO mCateDAO;
    private LiveData<List<Category>> mAllCate;

    public CategoryRepository(Application application) {
        CategoryDatabase db = CategoryDatabase.getDatabase(application);
        mCateDAO = db.categoryDAO();
        mAllCate = mCateDAO.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCate;
    }

    public void insert(Category word) {
        new insertAsyncTask(mCateDAO).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO mAsyncTaskDao;

        insertAsyncTask(CategoryDAO dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            mAsyncTaskDao.insert(categories[0]);
            return null;
        }
    }

    public void delete(Category category) {
        new deleteAsyncTask(mCateDAO).execute(category);
    }

    private static class deleteAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO mAsyncTaskDao;

        deleteAsyncTask(CategoryDAO dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            mAsyncTaskDao.delete(categories[0]);
            return null;
        }
    }

    public void update(Category category) {
        new updateAsyncTask(mCateDAO).execute(category);
    }

    private static class updateAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO mAsyncTaskDao;

        updateAsyncTask(CategoryDAO dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            mAsyncTaskDao.update(categories[0]);
            return null;
        }
    }
}
