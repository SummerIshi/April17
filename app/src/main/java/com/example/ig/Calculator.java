package com.example.ig;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener{
    private TextView solutionTv;
    private TextView resultTv;

    Button button0, button1,button2, button3,button4, button5,button6,button7,button8,button9,buttonPlus,buttonMinus,buttonMulti,buttonDiv,buttonDec,buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        solutionTv = findViewById(R.id.solutionTv);
        resultTv = findViewById(R.id.resultTv);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.buttonsAct_btn);
        button4 = findViewById(R.id.midterm_btn);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMulti= findViewById(R.id.buttonMult);
        buttonDiv= findViewById(R.id.buttonDiv);

        buttonDec = findViewById(R.id.buttonDec);
        buttonEqual = findViewById(R.id.buttonEqual);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);

        buttonDec.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String seqGiven = solutionTv.getText().toString();

        if (buttonText.equals("=")) {
            seqGiven = calculateData(seqGiven);

            resultTv.setText(seqGiven);
            solutionTv.setText("0");

        } else if (buttonText.equals(".")) {
            String last_num = "";
            for(int i = seqGiven.length()-1; i>=0; i--){
                if(isOperator(seqGiven.charAt(i))){
                    break;
                }
                last_num += seqGiven.charAt(i);
            }
            if(last_num.equals("")){
                seqGiven += "0";
                seqGiven += buttonText;
            } else if(last_num.contains(".")){
                if(last_num.charAt(0) == '.'){
                    seqGiven = seqGiven.substring(0,seqGiven.length() - 1);
                }
            } else {
                seqGiven += buttonText;
            }
            solutionTv.setText(seqGiven);
        }else {
            // Check if last input was an operation
            if (!seqGiven.isEmpty() && isOperator(seqGiven.charAt(seqGiven.length() - 1)) && isOperator(buttonText)) {
                // Replace the last operation with the new one
                seqGiven = seqGiven.substring(0, seqGiven.length() - 1);
            }
            String tempo = seqGiven;
            seqGiven += buttonText;
            solutionTv.setText(seqGiven);
            if(isOperator(buttonText)){
                resultTv.setText(sequentialCalculate(tempo));
            }
        }
    }
    // Helper method to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private boolean isOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("*")||s.equals("/");
    }
    private String calculateData(String data) {
        ArrayList<String> finalData = new ArrayList<>();
        // Extract numbers and operators
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                temp.append(c);
            } else {
                if (temp.length() > 0) {
                    finalData.add(temp.toString());
                    temp.setLength(0);
                }
                finalData.add(String.valueOf(c));
            }
        }
        if (temp.length() > 0) {
            finalData.add(temp.toString());
        }
        // Perform operations
        Stack<String> stackOp = new Stack<>();
        stackOp.push(finalData.get(0));
        for (int i = 1; i < finalData.size(); i++) {
            if (finalData.get(i).equals("+") || finalData.get(i).equals("-")) {
                stackOp.push(finalData.get(i));
            } else if (finalData.get(i).equals("*")) {
                double prevNum = Double.parseDouble(stackOp.pop());
                double nextNum = Double.parseDouble(finalData.get(++i));
                stackOp.push(String.valueOf(prevNum * nextNum));
            } else if (finalData.get(i).equals("/")) {
                double prevNum = Double.parseDouble(stackOp.pop());
                double nextNum = Double.parseDouble(finalData.get(++i));
                stackOp.push(String.valueOf(prevNum / nextNum));
            } else {
                stackOp.push(finalData.get(i));
            }
        }
        // Calculate result
        double result = Double.parseDouble(stackOp.get(0));
        for (int i = 1; i < stackOp.size(); i += 2) {
            if (stackOp.get(i).equals("+")) {
                result += Double.parseDouble(stackOp.get(i + 1));
            } else {
                result -= Double.parseDouble(stackOp.get(i + 1));
            }
        }
        return String.valueOf(result);
    }
    private String sequentialCalculate(String data){

        ArrayList<String> finalData = new ArrayList<>();

        // Extract numbers and operators
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                temp.append(c);
            } else {
                if (temp.length() > 0) {
                    finalData.add(temp.toString());
                    temp.setLength(0);
                }
                finalData.add(String.valueOf(c));
            }
        }
        if (temp.length() > 0) {
            finalData.add(temp.toString());
        }

        // Perform operations
        Stack<String> stackOp = new Stack<>();
        int start = 0;
        if(isOperator(finalData.get(0))){
            stackOp.push("0");
        } else {
            stackOp.push(finalData.get(0));
            start = 1;
        }

        for (int i = start; i < finalData.size(); i++) {
            if ( isOperator(finalData.get(i))) {
                if(finalData.get(i).equals("+")){
                    double prevNum = Double.parseDouble(stackOp.pop());
                    double nextNum = Double.parseDouble(finalData.get(++i));
                    stackOp.push(String.valueOf(prevNum + nextNum));
                } else if (finalData.get(i).equals("-")){
                    double prevNum = Double.parseDouble(stackOp.pop());
                    double nextNum = Double.parseDouble(finalData.get(++i));
                    stackOp.push(String.valueOf(prevNum - nextNum));
                } else if (finalData.get(i).equals("*")) {
                    double prevNum = Double.parseDouble(stackOp.pop());
                    double nextNum = Double.parseDouble(finalData.get(++i));
                    stackOp.push(String.valueOf(prevNum * nextNum));
                } else if (finalData.get(i).equals("/")) {
                    double prevNum = Double.parseDouble(stackOp.pop());
                    double nextNum = Double.parseDouble(finalData.get(++i));
                    stackOp.push(String.valueOf(prevNum / nextNum));
                }
            } else {
                stackOp.push(finalData.get(i));
            }
        }

        return stackOp.pop();
    }
}