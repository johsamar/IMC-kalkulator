package com.josamar.imckalkulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView lblTitle = null;
    private TextView lblWeight=null;
    private TextView lblHeight=null;
    private EditText inputWeight = null;
    private EditText inputHeight = null;
    private Button btnGet = null;
    private Button btnReset = null;
    private ImageView imageHomens = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
    }
    private void initViews(){
        lblTitle = findViewById(R.id.lblTitle);
        lblWeight = findViewById(R.id.lblWeight);
        lblHeight = findViewById(R.id.lblHeight);

        inputWeight = findViewById(R.id.inputWeight);
        inputHeight = findViewById(R.id.inputHeight);

        btnGet = findViewById(R.id.btnGet);
        btnReset = findViewById(R.id.btnReset);

        imageHomens = findViewById(R.id.imageHomens);
    }
    private void initEvents(){
        getDrawable(R.drawable.homens);
        btnGet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showResults();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resetAll();
            }
        });
    }
    private void showResults(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String weight = inputWeight.getText().toString();
        String height = inputHeight.getText().toString();
        if(weight.length()>0 && height.length()>0){
            double imc = getImc(Double.parseDouble(weight),Double.parseDouble(height));
            knowStatus(imc,alert);
        }else{
            alert.setMessage(getString(R.string.fields_empty));
            alert.setPositiveButton("Ok", null);
            alert.show();
        }
    }
    private void knowStatus(double imcResult,AlertDialog.Builder alert){
        StringBuilder text = new StringBuilder();
        alert.setTitle(getString(R.string.result));
        text.append(getString(R.string.your_imc_is,String.valueOf(imcResult)));
        text.append("\n");
        if(imcResult < 16){
            text.append(getString(R.string.thinness_s));
            alert.setIcon(getDrawable(R.drawable.degasevere));
        }else if(imcResult < 17) {
            text.append(getString(R.string.thinness_m));
            alert.setIcon(getDrawable(R.drawable.degamoderate));
        }else if(imcResult < 18.5){
            text.append(getString(R.string.thinness_a));
            alert.setIcon(getDrawable(R.drawable.delgacceptable));
        }else if(imcResult <25){
            text.append(getString(R.string.normal));
            alert.setIcon(getDrawable(R.drawable.normal));
        }else if(imcResult < 30){
            text.append(getString(R.string.overweight));
            alert.setIcon(R.drawable.sobrep);
        }else if(imcResult < 35){
            text.append(getString(R.string.obesity1));
            alert.setIcon(R.drawable.obs1);
        }else if(imcResult < 40){
            text.append(getString(R.string.obesity1));
            alert.setIcon(R.drawable.obs2);
        }else if(imcResult < 50){
            text.append(getString(R.string.obesity3));
            alert.setIcon(R.drawable.obs3);
        }else{
            text.append(getString(R.string.obesity4));
            alert.setIcon(R.drawable.obs4);
        }
        alert.setMessage(text);
        alert.setPositiveButton("Ok", null);
        alert.show();
    }
    public double getImc(double w,double h){
        DecimalFormat limit = new DecimalFormat("#.00");
        double result = Double.parseDouble(limit.format(w/(h*h)));
        return result;
    }
    private void resetAll(){
        inputWeight.setText("");
        inputHeight.setText("");
    }
}
