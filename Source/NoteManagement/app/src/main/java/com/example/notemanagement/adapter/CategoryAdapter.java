package com.example.notemanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.category.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final LayoutInflater mInflater;
    private List<Category> categories;
    public CategoryAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        if (categories != null) {
            Category current = categories.get(position);
            holder.tvCategory.setText("   Name: " + current.getNameCate());
            holder.tvDate.setText("   Created Date: " + current.getDateCate());
        }
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (categories != null)
            return categories.size();
        else return 0;
    }

     public List<Category> getCategories(){
        return categories;
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategory;
        private TextView tvDate;
        private ImageView ivEdit;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvName_cate);
            tvDate = itemView.findViewById(R.id.tvDate_cate);
            ivEdit = itemView.findViewById(R.id.ivEdit);

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();

                }
            });
        }
    }
}
