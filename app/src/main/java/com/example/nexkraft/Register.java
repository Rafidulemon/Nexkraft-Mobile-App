package com.example.nexkraft;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Register extends AppCompatActivity {
    private Animation blink;
    private Button btn_web;
    Button btn_reg;
    EditText f_name,l_name,e_id,b_date,email_reg,pass,confirm_pass;
    String emailPattern = "[a-zA-Z0-9._-]+@nexkraft.com";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    int year,month,day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        blink= AnimationUtils.loadAnimation(this,R.anim.blink);
        btn_web=findViewById(R.id.webmail);
        btn_web.setAnimation(blink);

        btn_web=(Button) findViewById(R.id.webmail);
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGetNKWebmail();
            }
        });

        //FirebaseAuth
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        f_name=findViewById(R.id.f_name);
        l_name=findViewById(R.id.l_name);
        e_id=findViewById(R.id.e_id);
        b_date=findViewById(R.id.b_date);
        Calendar calendar= Calendar.getInstance();

        email_reg=findViewById(R.id.email_reg);
        pass=findViewById(R.id.login_password);
        confirm_pass=findViewById(R.id.confirm_password);
        btn_reg= (Button) findViewById(R.id.btn_reg_confirm);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }
        });

        b_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month=month+1;
                        String date= dayOfMonth+"/"+month+"/"+year;
                        b_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
    }



    private void performAuth() {
        String email= email_reg.getText().toString();
        String password = pass.getText().toString();
        String confrimPassword= confirm_pass.getText().toString();
        String fName=f_name.getText().toString();
        String lName=l_name.getText().toString();
        String ID=e_id.getText().toString();
        String date=b_date.getText().toString();

        if (!email.matches(emailPattern)){
            email_reg.setError("Please enter valid email");
        }else if (password.isEmpty() || password.length()<6){
            pass.setError("Please enter proper password");
        }else if (!password.equals(confrimPassword)){
            confirm_pass.setError("Password doesn't match !");
        }else if (ID.length()<8){
            e_id.setError("Please enter proper ID");
        }
        else if (f_name.getText().toString().isEmpty()|| l_name.getText().toString().isEmpty()||e_id.getText().toString().isEmpty()||
                b_date.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill up all the fields.", Toast.LENGTH_LONG).show();
        }else {
            //progressbar
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        //Data write to firebase
                        Toast.makeText(Register.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        FirebaseDatabase rootnode=FirebaseDatabase.getInstance();
                        DatabaseReference reference=rootnode.getReference("Users");
                        User user=new User(fName,lName,ID,date,email,password);
                        reference.child(ID).setValue(user);
                        openLogin();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }


    public void openGetNKWebmail(){
        Intent i= new Intent(this, GetNKWebmail.class);
        startActivity(i);
    }
    public void openLogin(){
        Intent i= new Intent(this, Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}