package com.example.ig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class Batch1 extends AppCompatActivity implements View.OnClickListener {

    int[] colors = new int[3];
    Button[][] buttons = new Button[3][3];

    int[][] cells = new int[3][3];

    boolean done = false;
    Button ret;
    Button autowin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch1);

        ret = findViewById(R.id.return_btn);
        autowin = findViewById(R.id.btncheat);

        autowin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Win();
            }
        });
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
        buttons = new Button[][]{
                {findViewById(R.id.btn_00), findViewById(R.id.btn_01), findViewById(R.id.btn_02)},
                {findViewById(R.id.btn_10), findViewById(R.id.btn_11), findViewById(R.id.btn_12)},
                {findViewById(R.id.btn_20), findViewById(R.id.btn_21), findViewById(R.id.btn_22)}
        };
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setOnClickListener(this);
            }
        }
        colors = new int[]{R.color.color1, R.color.color2, R.color.color3};
        cells = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        random();
        syncCells();
    }
    @Override
    public void onClick(View view) {
        Button b = (Button) view;

        if(!isDone()){
            changePlusColor(b);
        } else {
            Toast.makeText(getApplicationContext(), " SHEESHH!!! YOU WIN", Toast.LENGTH_SHORT).show();
        }
    }
    public int randomColor(){
        Random r = new Random();
        return r.nextInt(3);
    }
    public void changePlusColor(Button b){
        int row =0, col = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(b == buttons[i][j]){
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if(row - 1 >= 0){
            cells[row - 1][col] =  (cells[row - 1][col] + 1) % 3;
        }
        if(row + 1 < 3){
            cells[row + 1][col] =  (cells[row + 1][col] + 1) % 3;
        }
        if(col - 1 >= 0){
            cells[row][col - 1] =  (cells[row][col - 1] + 1) % 3;
        }
        if(col + 1 < 3){
            cells[row][col + 1] =  (cells[row][col + 1] + 1) % 3;
        }
        syncCells();
    }
    public void random(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cells[i][j] = randomColor();
            }
        }
    }
    public void syncCells(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                buttons[i][j].setBackgroundColor(ContextCompat.getColor(this, colors[cells[i][j]]));
            }
        }
    }
    public boolean isDone(){
        int temp = cells[0][0];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(temp != cells[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public void restartGame(){
        random();
        syncCells();
    }
    public void Win(){
        cells = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };
        syncCells();
    }
}