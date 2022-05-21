package com.example.nexkraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler h=new Handler();
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.imageView);
        image.setAnimation(topAnim);

        credit=findViewById(R.id.credit);
        credit.setAnimation(bottomAnim);


        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();

            }
        },5000);
    }
}