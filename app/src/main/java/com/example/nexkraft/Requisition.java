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
import java.util.Date;
import java.util.Locale;

public class Requisition extends AppCompatActivity {
    EditText dateformat;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dateformat=findViewById(R.id.datepick);
        Calendar calendar= Calendar.getInstance();
        btn= (Button)findViewById(R.id.req_req);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegMsg();
            }
        });

        dateformat.setText(getTodaysDate());
    }

    private void openRegMsg() {
        Intent i=new Intent(this,ReqMsg.class);
        startActivity(i);
    }
    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(new Date());
    }

}