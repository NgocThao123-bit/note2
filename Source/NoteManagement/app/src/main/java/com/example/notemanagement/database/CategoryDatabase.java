package com.example.notemanagement.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notemanagement.dao.CategoryDAO;
import com.example.notemanagement.ui.category.Category;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();

    private static CategoryDatabase INSTANCE;

    public static CategoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CategoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryDatabase.class, "category_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CategoryDAO mDao;
        String[] cate = {};

        PopulateDbAsync(CategoryDatabase db) {
            mDao = db.categoryDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

//            mDao.deleteAllCate();

            for (int i = 0; i <= cate.length - 1; i++) {
                Category category = new Category(cate[i]);
                mDao.insert(category);
            }
            return null;
        }
    }
}

