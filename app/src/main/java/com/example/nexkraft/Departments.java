package com.example.nexkraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Departments extends AppCompatActivity {
    CardView admin,it,business,sister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        admin=(CardView)findViewById(R.id.cardview_admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdmin();
            }
        });

        it=(CardView)findViewById(R.id.cardview_it);
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openITTeam();
            }
        });

        business=(CardView)findViewById(R.id.cardview_business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBusiness();
            }
        });

        sister=(CardView)findViewById(R.id.cardview_sister);
        sister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSister();
            }
        });
    }

    private void openSister() {
        Intent i= new Intent(this, Sister.class);
        startActivity(i);
    }

    private void openBusiness() {
        Intent i= new Intent(this, Business.class);
        startActivity(i);
    }

    private void openITTeam() {
        Intent i= new Intent(this, ITTeam.class);
        startActivity(i);
    }

    private void openAdmin() {
        Intent i= new Intent(this, Admin.class);
        startActivity(i);
    }
}