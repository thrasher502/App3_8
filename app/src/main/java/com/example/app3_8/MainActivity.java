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
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double interestRate=0.0;
    private double downPayment = 0.0;
    private double loanAmount = 0.0;
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
        update();
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








    }


    private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            numMonths = progress+24;

            update();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private double inputValidation(CharSequence s) {
        double x;

                try {

                    x = Double.parseDouble(s.toString());

                    return x;

                } catch (NumberFormatException e) {
                    x = 0.0;
                    return x;
                }
    }

    private double calcPayment(double iR,double dP, double lA, int noMon){
        return (lA-dP)* Math.pow((1+iR),(noMon/12));
    }

    private void update(){
        paymentTextView.setText(currencyFormat.format((calcPayment(interestRate,downPayment,loanAmount,numMonths))));
        yr2TextView.setText(currencyFormat.format((calcPayment(interestRate,downPayment,loanAmount,24))));
        yr3TextView.setText(currencyFormat.format((calcPayment(interestRate,downPayment,loanAmount,36))));
        yr4TextView.setText(currencyFormat.format((calcPayment(interestRate,downPayment,loanAmount,48))));
        iRTextView.setText(percentFormat.format(interestRate));
        downPaymentTextView.setText(currencyFormat.format(downPayment));
        monthsTextView.setText(String.valueOf(numMonths));
        loanTextView.setText(currencyFormat.format(loanAmount));
    }
}

