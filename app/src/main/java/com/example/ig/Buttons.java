package com.example.ig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Buttons extends AppCompatActivity {
    Button closebtn;
    Button toastbtn;
    Button changebg;
    Button changebtnbg;
    Button disbtn;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        closebtn = findViewById(R.id.closebtn);
        toastbtn = findViewById(R.id.toastbtn);
        changebg = findViewById(R.id.chngbgbtn);
        changebtnbg = findViewById(R.id.chngbtnbg);
        disbtn = findViewById(R.id.disbtn);

        closebtn.setOnClickListener((view)-> {
            Intent intent1 = new Intent(Buttons.this,Buttons2.class);
            startActivity(intent1);
        });

        toastbtn.setOnClickListener((view)-> {
            Toast.makeText(this,"This is a toast button..", Toast.LENGTH_LONG).show();
        });

        changebg.setOnClickListener((view )-> {
            ConstraintLayout layout = findViewById(R.id.HomeLayout);
            layout.setBackgroundColor(0xFF6C0345);
        });
        changebtnbg.setOnClickListener((view) ->{
            changebtnbg.setBackgroundColor(R.color.color5);
        });
        disbtn.setOnClickListener((view) ->{
            disbtn.setVisibility(View.INVISIBLE);
        });
    }


}