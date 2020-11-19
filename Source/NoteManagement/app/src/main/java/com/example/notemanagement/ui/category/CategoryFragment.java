package com.example.notemanagement.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.CacheDispatcher;
import com.example.notemanagement.AppExecutors;
import com.example.notemanagement.R;
import com.example.notemanagement.adapter.CategoryAdapter;
import com.example.notemanagement.database.CategoryDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;
    private FloatingActionButton fab;
    private CategoryDatabase categoryDatabase;
    private CategoryAdapter adapter;


    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        mViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        adapter = new CategoryAdapter(getContext());
        final RecyclerView recyclerView = root.findViewById(R.id.rvCategory);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fab = root.findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditCategory.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
        mViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                // Update the cached copy of the words in the adapter.
                adapter.setCategories(categories);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
//                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                       getActivity().runOnUiThread(new Runnable() {
//                           @Override
//                           public void run() {
//                               categoryDatabase = Room.databaseBuilder(getActivity(), CategoryDatabase.class, "category-database").allowMainThreadQueries().build();
//                               int position = viewHolder.getAdapterPosition();
//                               LiveData<List<Category>> tasks = (LiveData<List<Category>>) adapter.getCategories();
//                               categoryDatabase.categoryDAO().delete(tasks.get(position));
//                               retrieveTasks();
//                           }
//                       });
                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Category> categories = adapter.getCategories();
                        mViewModel.delete(categories.get(position));
                    }
                });
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        retrieveTasks();
//    }
//    private void retrieveTasks(){
//        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter.setCategories((List<Category>) categoryDatabase);
//                    }
//                });
//            }
//        });
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel
    }

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Category category = new Category(data.getStringExtra(EditCategory.EXTRA_REPLY));
            mViewModel.insert(category);
        } else {
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    "R.string.empty_not_saved",
                    Toast.LENGTH_LONG).show();
        }
    }

}