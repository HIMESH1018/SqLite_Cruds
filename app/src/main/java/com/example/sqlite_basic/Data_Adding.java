package com.example.sqlite_basic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sqlite_basic.Database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Data_Adding extends AppCompatActivity {

    EditText email,password;
    AppCompatButton Add;
    ImageView imageView;
    DBHelper DB;
    String i1;
    String img;
    byte[] bytesImage;
    int i = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 0:
                if (resultCode == RESULT_OK) {
                    Uri targetUri = data.getData();
                    //             textTargetUri.setText(targetUri.toString());
                    Bitmap bitmap;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                        imageView.setImageBitmap(bitmap);

                        i1 = bitmap.toString();
                        Log.i("firstimage........", "" + i1);
                       // imageView.setVisibility(0);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
                        bytesImage = stream.toByteArray();

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_adding);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Add = findViewById(R.id.add);
        imageView = findViewById(R.id.image);
        DB = new DBHelper(this);

        Add.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Pass = password.getText().toString();
                Log.e("CheckImage",""+i1);

                Boolean checkinsert = DB.insertDetails(Email,Pass,bytesImage);
                if(checkinsert){
                    Toast.makeText(Data_Adding.this, "Data Added to the DB", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Data_Adding.this, "Data Adding Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Log.i("photo", "" + intent);
                startActivityForResult(intent, i);
                i = i + 1;

            }
        });





    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}