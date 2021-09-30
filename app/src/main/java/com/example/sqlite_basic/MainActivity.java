package com.example.sqlite_basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AppCompatButton Add, DView,Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add = findViewById(R.id.Data_Add);
        DView = findViewById(R.id.Data_View);
        Update = findViewById(R.id.Data_update);


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(MainActivity.this,Data_Adding.class);
                startActivity(i);
            }
        });

        DView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,DataView.class);
                startActivity(i);
            }
        });


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,Data_Update.class);
                startActivity(i);
            }
        });
    }
}