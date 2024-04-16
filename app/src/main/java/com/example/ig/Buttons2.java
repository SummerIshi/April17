package com.example.ig;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

public class Buttons2 extends AppCompatActivity {
    Button returnbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons2);

        returnbtn = findViewById(R.id.returnbtn);

        returnbtn.setOnClickListener((view)-> {
            finish();
        });
    }
}