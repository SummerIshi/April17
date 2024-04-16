package com.example.ig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Batch1_Connect3 extends AppCompatActivity {

    TextView playerturn;
    Button[][] btn;
    boolean Win;
    int[][] grid = new int[5][5];

    int playerTurn = 1;

    int[] color = new int[]{R.color.empty, R.color.player1, R.color.player2};

    boolean isDone = false;

    Button res;

    @SuppressLint({"DiscouragedApi", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch1_connect3);

        btn = new Button[5][5];
        res = findViewById(R.id.restartbtn);
        playerturn = findViewById(R.id.turn_textview);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                String temp = "btn_" + row + col;
                btn[row][col] = findViewById(this.getResources().getIdentifier(temp, "id", this.getPackageName()));

                if (row != 0) {
                    btn[row][col].setEnabled(false);
                }
            }
        }

        syncCells();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
               int coll = col;
                btn[row][col].setOnClickListener(view -> {
                    coinDrop(coll);
                });
            }
        }
    }
    public void syncCells(){
        for(int r = 0; r < 5 ; r++){
            for(int c = 0; c <5; c++){
                btn[r][c].setBackgroundColor(ContextCompat.getColor(this,color[grid[r][c]]));
            }
        }
    }
    public void coinDrop(int col){



        if (isDone){
            return;
        }

        for(int row = 4; row >= 0; row--){
            if(grid[row][col] == 0){
                grid[row][col] = playerTurn;
                syncCells();
                genCheck();
                nextPlayerTurn();
                break;
            }
        }
    }
    @SuppressLint("SetTextI18n")
    public void nextPlayerTurn(){
        if(playerTurn == 1){
            playerTurn = 2;
        }else{
            playerTurn = 1;
        }
        playerturn.setText("Player " + playerTurn + "'s Turn");
    }

public void genCheck() {
    for (int row = 0; row < 5; row++) {
        for (int col = 0; col < 5; col++) {
            if (grid[row][col] == playerTurn && Check(row, col, 0, 1) || Check(row, col, 1, 0) ||
                    Check(row, col, 1, 1) || Check(row, col, 1, -1)) {
                isDone = true;
                Toast.makeText(getApplicationContext(), "PLAYER " + (playerTurn) + " WINS! SHEESHH", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}

public boolean Check(int row, int col, int tempRow, int tempCol) {
        if (row < 0 || row >= 5 || col < 0 || col >= 5 || grid[row][col] != playerTurn) {
            return false;
        }
        if (grid[row][col] == playerTurn && colorCtr(row, col, tempRow, tempCol, 1) == 3) {
            return true;
        }
        return Check(row + tempRow, col + tempCol, tempRow, tempCol);
    }

    public int colorCtr(int row, int col, int tempRow, int tempCol, int count) {
        if (row + tempRow < 0 || row + tempRow >= 5 || col + tempCol < 0 || col + tempCol >= 5 ||
                grid[row + tempRow][col + tempCol] != playerTurn) {
            return count;
        }
        return colorCtr(row + tempRow, col + tempCol, tempRow, tempCol, count + 1);
    }
    public void restart(){
        playerTurn = 1;
        isDone = false;
        for(int r = 0; r < 5 ; r++){
            for(int c = 0; c <5; c++){
                grid[r][c] = 0;
            }
        }
        playerturn.setText("Player " + playerTurn + "'s Turn");
        syncCells();
    }

}