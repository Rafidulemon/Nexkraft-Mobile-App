package com.example.nexkraft;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    private ImageView img;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    CardView leave_application,departments,attendance_history,leave_quota,cancel_meal,meal_details,requisition,tada_bill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.navigation_view);

        departments=(CardView)findViewById(R.id.cardview_1);
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDepartments();
            }
        });

        attendance_history=(CardView) findViewById(R.id.cardview_2);
        attendance_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAttendanceHIstory();
            }
        });

        leave_application= (CardView)findViewById(R.id.cardview_3);
        leave_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaveApplication();
            }
        });

        leave_quota=(CardView) findViewById(R.id.cardview_4);
        leave_quota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaveQuota();
            }
        });

        cancel_meal=(CardView) findViewById(R.id.cardview_5);
        cancel_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCancelMeal();
            }
        });

        meal_details=(CardView) findViewById(R.id.cardview_6);
        meal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMealDetails();
            }
        });

        requisition=(CardView) findViewById(R.id.cardview_7);
        requisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequisition();
            }
        });

        tada_bill=(CardView) findViewById(R.id.cardview_8);
        tada_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLTadaBill();
            }
        });

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        img= (ImageView) findViewById(R.id.profile_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void openProfile(){
        Intent i=new Intent(this, Profile.class);
        Bundle b= ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(i, b);
    }
    public void openDepartments(){
        Intent i= new Intent(this, Departments.class);
        startActivity(i);
    }

    public void openLeaveApplication(){
        Intent i= new Intent(this, LeaveApplication.class);
        startActivity(i);
    }
    public void openAttendanceHIstory(){
        Intent i= new Intent(this, AttendanceHIstory.class);
        startActivity(i);
    }
    public void openLeaveQuota(){
        Intent i= new Intent(this, LeaveQuota.class);
        startActivity(i);
    }
    public void openCancelMeal(){
        Intent i= new Intent(this, CancelMeal.class);
        startActivity(i);
    }
    public void openMealDetails(){
        Intent i= new Intent(this, MealDetails.class);
        startActivity(i);
    }
    public void openRequisition(){
        Intent i= new Intent(this, Requisition.class);
        startActivity(i);
    }
    public void openLTadaBill(){
        Intent i= new Intent(this, TadaBill.class);
        startActivity(i);
    }

}