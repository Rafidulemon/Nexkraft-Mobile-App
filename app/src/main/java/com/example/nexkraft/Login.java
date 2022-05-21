package com.example.nexkraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Button btn_login;
    private Button btn_reg,forget_pass;
    EditText login_email,login_password;
    String emailPattern = "[a-zA-Z0-9._-]+@nexkraft.com";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         btn_login= (Button) findViewById(R.id.loginbtn);
         btn_reg= (Button) findViewById(R.id.reg_btn);
         forget_pass=(Button) findViewById(R.id.forget_pass);
         forget_pass.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openRegister();
             }
         });
         btn_reg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openRegister();
             }
         });

         login_email=findViewById(R.id.login_email);
         login_password=findViewById(R.id.login_password);


        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

    }




    //firebase login
    private void performLogin() {
        String email= login_email.getText().toString();
        String password = login_password.getText().toString();

        if (!email.matches(emailPattern)){
            login_email.setError("Please enter valid email");
        }else if (password.isEmpty() || password.length()<6){
            login_password.setError("Please enter correct password");
        }else {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        openDashboard();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void openDashboard() {
        Intent i= new Intent(this, Dashboard.class);
        startActivity(i);
    }    public void openRegister() {
        Intent i= new Intent(this, Register.class);
        startActivity(i);
    }

}