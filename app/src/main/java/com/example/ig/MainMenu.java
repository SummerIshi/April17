package com.example.ig;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button Ig;
    Button calcbtn;
    Button midterm;

    Button connect;

    Button buttonsAct;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Ig = findViewById(R.id.IgBtn);
        calcbtn = findViewById(R.id.calcbtn);
        midterm = findViewById(R.id.midterm_btn);
        buttonsAct = findViewById(R.id.buttonsAct_btn);
        connect = findViewById(R.id.connectbtn);

        Ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenu.this, Instagram.class);
                startActivity(intent);
            }
        });

        calcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Calculator.class);
                startActivity(intent);
            }
        });

        midterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Batch1.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Summer Ishi P. Rodrigo - COLORMATCHING", Toast.LENGTH_SHORT).show();
            }
        });

        buttonsAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Buttons.class);
                startActivity(intent);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Batch1_Connect3.class);
                startActivity(intent);
            }
        });




    }
}
