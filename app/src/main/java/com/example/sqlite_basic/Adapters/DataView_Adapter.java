package com.example.sqlite_basic.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sqlite_basic.DataView;
import com.example.sqlite_basic.Database.DBEntry;
import com.example.sqlite_basic.Database.DBHelper;
import com.example.sqlite_basic.Models.User;
import com.example.sqlite_basic.R;

import java.util.ArrayList;

public class DataView_Adapter extends RecyclerView.Adapter<DataView_Adapter.ViewHolder> {

    private Cursor cursor;
    private Context context;
    private onItemClickListner mlistner;

    public DataView_Adapter(Cursor cursor, Context context) {
        this.cursor = cursor;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_userdetails,parent,false);
        return new DataView_Adapter.ViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(!cursor.moveToPosition(position)){
            return;
        }
        @SuppressLint("Range") String mail = cursor.getString(cursor.getColumnIndex(DBEntry.GroceryEntry.COLUMN_EMAIL));
        @SuppressLint("Range") String pass  = cursor.getString(cursor.getColumnIndex(DBEntry.GroceryEntry.COLUMN_PASSWORD));
        @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(DBEntry .GroceryEntry._ID));
        @SuppressLint("Range") byte [] img = cursor.getBlob(cursor.getColumnIndex(DBEntry.GroceryEntry.COLUMN_IMAGE));
        holder.email.setText(mail);
        holder.password.setText(pass);
        holder.id.setText(""+id);
        holder.itemView.setTag(id);

        Glide.with(context)
                .load(img)
                .fitCenter()
                .placeholder(R.drawable.coffee_cup_logo)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email,password,id;
        CardView cardView;
        ImageView image;

        public ViewHolder(@NonNull View itemView, onItemClickListner clickListner) {
            super(itemView);

            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.pass);
            cardView = itemView.findViewById(R.id.cardview);
            image = itemView.findViewById(R.id.image);
            id = itemView.findViewById(R.id.id);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DBEntry.GroceryEntry._ID));
                     id = String.valueOf(cursor.getInt(getAdapterPosition()));
                    mlistner.onCardClick(id);
                }
            });


        }
    }

    public interface onItemClickListner{
        void onCardClick(String itemID);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        mlistner = listner;
    }


}
