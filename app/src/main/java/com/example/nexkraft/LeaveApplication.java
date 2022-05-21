package com.example.nexkraft;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LeaveApplication extends AppCompatActivity{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    Button leave_apply,leave_view,leave_send;
    EditText leave_name,leave_email,leave_id,leave_reason,leave_date;
    int year,month,day;

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //toolbar declare
        toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.navigation_view);

        //toolbar toggle
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //pdf buttons onclick
        leave_apply= (Button) findViewById(R.id.leave_apply);
        leave_name= (EditText) findViewById(R.id.leave_name);
        leave_email= (EditText) findViewById(R.id.leave_email);
        leave_id=(EditText) findViewById(R.id.leave_id);
        leave_date=(EditText) findViewById(R.id.leave_date);
        Calendar calendar= Calendar.getInstance();
        leave_reason=(EditText) findViewById(R.id.leave_reason);
        leave_view=(Button)findViewById(R.id.leave_view);
        leave_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewPdf();
            }
        });

        leave_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(LeaveApplication.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month=month+1;
                        String date= dayOfMonth+"/"+month+"/"+year;
                        leave_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        //send mail to hr
        leave_send=(Button)findViewById(R.id.leave_send);
        leave_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendApplication();
            }
        });


        leave_apply.setOnClickListener(new View.OnClickListener() {

            //leave_apply button onClick
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.leave_apply) {
                    if (!leave_name.getText().toString().isEmpty() && !leave_email.getText().toString().isEmpty()) {
                        try {
                            leave_apply("Leave application", leave_name.getText().toString(),
                                    leave_email.getText().toString(),
                                    leave_id.getText().toString(),
                                    leave_reason.getText().toString(),
                                    leave_date.getText().toString());

                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please fill up all the fields.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void sendApplication() {
        String to="tusher.nexkraft@gmail.com";
        String subject="Application for leave.";
        String message="To \nMahmudul Hasan Tusher\nHR Manager\nNexkraft Limited.\n\nDate: "+getTodaysDate()+
                "\n\nSir,\n\nMost respectfully, I beg to state that, I am not in a condition to come to office on "+leave_date.getText().toString().trim()+"."+" The reason is :"+"'"+
                leave_reason.getText().toString().trim()+"'. Hence, kindly grant my leave of absence.\n\nI shall be really grateful to you.\n\nThanking you !"+"\n\nRegards" + "\n" +
                "Name: "+leave_name.getText().toString().trim()+"\nEmployee ID: " + leave_id.getText().toString().trim() +
                "\n" + "Email Address :" + leave_email.getText().toString().trim();


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }


    public void leave_apply(String title, String name, String email, String id, String reason, String date) throws DocumentException {

        String path = getExternalFilesDir(null).getAbsolutePath().toString()+"Application_"+getCurrentTime() + "_" + getTodaysDate() + "leave.pdf";
        File file = new File(path);

        if (!file.exists()){
            try {
                file.createNewFile();
        } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Document document = new Document(PageSize.A5);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        }


        document.open();

        //title
        Paragraph title_para = new Paragraph();
        title_para.add(title);
        title_para.setSpacingAfter(10.0f);
        title_para.setAlignment(Element.ALIGN_CENTER);
        document.add(title_para);

        //body paragraph
        Paragraph body_para = new Paragraph();
        body_para.add("To \nMahmudul Hasan Tusher\nHR Manager\nNexkraft Limited.\n\nDate: "+getTodaysDate()+"\n\nSubject: Application for leave."+
                "\n\nSir,\n\nMost respectfully, I beg to state that, I am not in a condition to come to office on "+date+"."+" The reason is :"+"'"+
                reason+"'. Hence, kindly grant my leave of absence.\n\nI shall be really grateful to you.\n\nThanking you !");
        document.add(body_para);

        //Regards paragraph
        Paragraph regards_para = new Paragraph();
        regards_para.add("\n\nRegards" + "\n" + "Name: "+name+"\nEmployee ID: " + id + "\n" + "Email Address :" + email);
        document.add(regards_para);


        Toast.makeText(this,"Your PDF is saved !! ",Toast.LENGTH_SHORT).show();
        document.close();

    }


    private String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(new Date());
    }


    //pdf view activity open
    public void openViewPdf(){
        Intent i= new Intent(this, ViewPdf.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
