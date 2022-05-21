package com.example.nexkraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class GetNKWebmail extends AppCompatActivity {

    private Button btn;
    EditText wm_name,wm_id,wm_number,wm_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_n_k_webmail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wm_name=findViewById(R.id.wm_name);
        wm_email=findViewById(R.id.wm_email);
        wm_id=findViewById(R.id.wm_id);
        wm_number=findViewById(R.id.wm_number);


        

        btn= (Button) findViewById(R.id.send_webmail_rqst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();

            }
        });

    }

    private void sendMail() {
        /*String email="rafidulemon@gmail.com";
        String subject= "Webmail request !";
        String body= "Dear sir\n\n I'm "+wm_name.getText().toString().trim()+". I need nexkraft webmail.\n\nEmployee ID: "+
                wm_id.getText().toString().trim()+"\nContact Number: "+wm_number.getText().toString().trim();

        String mail= "mailto" + email + "?&subject=" + Uri.encode(subject)+"&body="+Uri.encode(body);
        Intent i= new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse(mail));
        try {
            startActivity(Intent.createChooser(i,"send email.."));
        } catch (Exception e) {
            Toast.makeText(GetNKWebmail.this,"Exception: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }*/

        String to="tusher.nexkraft@gmail.com";
        String subject="Webmail request !";
        String message="Dear sir\n\n I'm "+wm_name.getText().toString().trim()+". I need nexkraft webmail.\n\nEmployee ID: "+
                wm_id.getText().toString().trim()+"\nContact Number: "+wm_number.getText().toString().trim();


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}




/*Note: If you use 2-Step-Verification and are seeing a "password incorrect" error when trying to access your Google Account, an App Password may solve the problem.

Go to your Google Account.
On the left navigation panel, choose Security.
On the "Signing in to Google" panel, choose App Passwords. If you don’t see this option:
2-Step Verification is not set up for your account.
2-Step Verification is set up for security keys only.
Your account is through work, school, or other organization.
You’ve turned on Advanced Protection for your account.
At the bottom, choose Select app and choose the app you’re using.
Choose Select device and choose the device you’re using.
Choose Generate.
Follow the instructions to enter the App Password. The App Password is the 16-character code in the yellow bar on your device.
Choose Done.*/