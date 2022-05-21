package com.example.nexkraft;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TadaBill extends AppCompatActivity {
    EditText dateformat;
    int year,month,day;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tada_bill);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dateformat=findViewById(R.id.datepick);
        Calendar calendar= Calendar.getInstance();
        btn= (Button)findViewById(R.id.req_req);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentamsg();
            }
        });
        dateformat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(TadaBill.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,int year, int month, int dayOfMonth) {

                        month=month+1;
                        String date= dayOfMonth+"/"+month+"/"+year;
                        dateformat.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
    }

    private void opentamsg() {
        Intent i=new Intent(this,tamsg.class);
        startActivity(i);
    }
    }