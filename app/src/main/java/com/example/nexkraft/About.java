package com.example.nexkraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {
    TextView about;
    Animation bottomAnim;
    Button ceo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*about=findViewById(R.id.textView_about);
        about.setAnimation(bottomAnim);

        ceo=findViewById(R.id.ceo);
        ceo.setAnimation(bottomAnim);*/
    }
}