package com.example.app3_8;

import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.NumberFormat;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.text.TextWatcher;
import android.text.Editable;
import java.math.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    private static final NumberFormat PERRCENT_FORMAT = NumberFormat.getPercentInstance();
    private static final int YEAR=12;

    private float interestRate=0.0f;
    private float downPayment = 0.0f;
    private float loanAmount = 0.0f;
    private int numMonths=24;
    private TextView loanTextView;
    private TextView downPaymentTextView;
    private TextView iRTextView;
    private TextView monthsTextView;
    private TextView paymentTextView;
    private TextView yr2TextView, yr3TextView, yr4TextView;
    private EditText loanEditText;
    private EditText downPaymentEditText;
    private EditText iREditText;
    private SeekBar seekBar;
    
    private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            numMonths = progress+24;
            update();
        }
        
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loanTextView = (TextView) findViewById(R.id.loanTextView);
        downPaymentTextView = (TextView) findViewById(R.id.downPaymentTextView);
        iRTextView = (TextView) findViewById(R.id.iRTextView);
        monthsTextView = (TextView)findViewById(R.id.monthsTextView);
        paymentTextView =(TextView) findViewById(R.id.paymentTextView);
        yr2TextView=(TextView)findViewById(R.id.yr2TextView);
        yr3TextView=(TextView)findViewById(R.id.yr3TextView);
        yr4TextView=(TextView)findViewById(R.id.yr4TextView);
        loanEditText = (EditText) findViewById(R.id.loanEditText);
        downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        iREditText = (EditText) findViewById(R.id.iREditText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        loanEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loanAmount=inputValidation(s);
                update();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        downPaymentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                downPayment=inputValidation(s);
                update();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
        iREditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                interestRate=inputValidation(s)/100;
                update();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
        update();
    }

    private float inputValidation(CharSequence s) {
        float x=0.0f; //you neeed to set input type for the edit text to numeric in xml and get rid of try catch block
        x = Double.parseDouble(s.toString()); 
        return x;
    }

    private float calcPayment(float iR,float dP, float lA, int noMon){
        return (float)((lA-dP)* Math.pow((1+iR),(noMon/12)));
    }

    private void update(){
        paymentTextView.setText(CURRENCY_FORMAT.format((calcPayment(interestRate,downPayment,loanAmount,numMonths))));
        yr2TextView.setText(CURRENCY_FORMAT.format((calcPayment(interestRate,downPayment,loanAmount,2*YEAR))));
        yr3TextView.setText(CURRENCY_FORMAT.format((calcPayment(interestRate,downPayment,loanAmount,3*YEAR))));
        yr4TextView.setText(CURRENCY_FORMAT.format((calcPayment(interestRate,downPayment,loanAmount,4*YEAR))));
        iRTextView.setText(PERRCENT_FORMAT.format(interestRate));
        downPaymentTextView.setText(CURRENCY_FORMAT.format(downPayment));
        monthsTextView.setText(String.valueOf(numMonths));
        loanTextView.setText(CURRENCY_FORMAT.format(loanAmount));
    }
}
