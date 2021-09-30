package com.example.sqlite_basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite_basic.Adapters.DataView_Adapter;
import com.example.sqlite_basic.Database.DBEntry;
import com.example.sqlite_basic.Database.DBHelper;
import com.example.sqlite_basic.Models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataView extends AppCompatActivity {

    TextView email,pass;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    DataView_Adapter adapter;
    ArrayList<User> modelArrayList;
    private SQLiteDatabase mdatabase;
    private DBEntry.GroceryEntry data;
    Cursor cData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        recyclerView = findViewById(R.id.recycler);
        modelArrayList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        mdatabase = dbHelper.getWritableDatabase();
        SetRecycler();



    }
    private void Dataview(){

        Cursor cursor = dbHelper.ViewDetails();

        if (cursor.getCount() == 0){
            Toast.makeText(DataView.this, "No Data to Display", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Email: "+cursor.getString(0)+"\n");
                buffer.append("Password: "+cursor.getString(0)+"\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(DataView.this);
            builder.setCancelable(true);
            builder.setTitle("User Details");
            builder.setMessage(buffer.toString());
            builder.show();
        }
    }

    private Cursor getAllItems() {
        return mdatabase.query(
                DBEntry.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DBEntry.GroceryEntry._ID + " ASC"
        );
    }


    private void SetRecycler(){

        adapter = new DataView_Adapter(getAllItems(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(new DataView_Adapter.onItemClickListner() {
            @Override
            public void onCardClick(String itemId) {
                Log.d("TAG", "onItemClick: "+itemId);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if(direction == ItemTouchHelper.RIGHT){
                    Toast.makeText(DataView.this, "Right"+viewHolder.itemView.getTag(), Toast.LENGTH_SHORT).show();
                }
                else if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(DataView.this, "Left"+viewHolder.itemView.getTag(), Toast.LENGTH_SHORT).show();
                }
                //removeItem((long) viewHolder.itemView.getTag());
//                Intent i = new Intent(DataView.this,Data_Update.class);
//                startActivity(i);


            }
        }).attachToRecyclerView(recyclerView);
    }

    private void removeItem(long id){

        mdatabase.delete(DBEntry.GroceryEntry.TABLE_NAME, DBEntry.GroceryEntry._ID + "=" + id,null);
        adapter.swapCursor(getAllItems());
    }

}