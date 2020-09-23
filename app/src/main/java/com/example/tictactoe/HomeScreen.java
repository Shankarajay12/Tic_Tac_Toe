package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private  Button first;
    private Button second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);

        first=(Button) findViewById(R.id.First);
        second=(Button) findViewById(R.id.Second);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingle();
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDouble();
            }
        });

    }

    public  void openSingle()
    {
        Intent intent=new Intent((HomeScreen.this),MainActivity.class);
        startActivity(intent);
    }

    public  void openDouble()
    {
        Intent intent=new Intent(HomeScreen.this,Second.class);
        startActivity(intent);
    }



    }

