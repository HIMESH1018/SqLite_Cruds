package com.example.sqlite_basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite_basic.Database.DBHelper;

public class Data_Update extends AppCompatActivity {

    EditText email,pass;
    AppCompatButton update,delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_update);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString();
                String Pass = pass.getText().toString();

//                Boolean checkinsert = DB.UpdteDetails("2",Email,Pass);
//                if(checkinsert){
//                    Toast.makeText(Data_Update.this, "Data Updated to the DB", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(Data_Update.this, "Data Updating Failed", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Boolean CheckDelete = DB.DeleteDetails("2");
//                if(CheckDelete){
//                    Toast.makeText(Data_Update.this, "Data Deleted from DB", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(Data_Update.this, "Data Deleting Failed", Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }
}