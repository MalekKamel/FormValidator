package com.sha.formvalidatorsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sha.formvalidatorsample.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Vh> {

    private static List<FieldItem> items = FieldInfo.items;

    public class Vh extends RecyclerView.ViewHolder {
       private TextView tvTitle;

        public Vh(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(__ -> items.get(getAdapterPosition()).showDemo(itemView.getContext()));
        }

        public void onBind(FieldItem item){
            tvTitle.setText(item.title);
        }
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_example, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
