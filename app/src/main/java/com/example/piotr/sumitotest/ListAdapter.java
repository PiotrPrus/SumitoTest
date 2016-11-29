package com.example.piotr.sumitotest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Piotr on 28.11.2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private ArrayList<Entry> entries;

    public ListAdapter(ArrayList<Entry> entries) {
        this.entries = entries;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder, int position) {

        viewHolder.listTextView.setText(entries.get(position).getEmployeeId());
        viewHolder.uuidTextView.setText(entries.get(position).getUuid());

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       public TextView listTextView;
        public TextView uuidTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            listTextView = (TextView) itemView.findViewById(R.id.list_text_view);
            uuidTextView = (TextView) itemView.findViewById(R.id.uuid_text_view);
        }
    }



}
