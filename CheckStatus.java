package com.example.ali.fastmainappfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.view.MenuItem;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckStatus extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView p_title, p_desc, p_loc, p_status;
    String title;
    Problem problem;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        problem= new Problem();
        p_title= (TextView) findViewById(R.id.problem_title);
        p_desc= (TextView) findViewById(R.id.problem_Description1);
        p_loc= (TextView) findViewById(R.id.problem_loc);
        p_status= (TextView) findViewById(R.id.problem_status);
        Intent i2= getIntent();
        title=i2.getStringExtra("TITLE");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Problems");
        DatabaseReference pid= myRef.child(title);
        pid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                p_desc.setText(dataSnapshot.child("Problem Description").getValue(String.class));
                p_loc.setText(dataSnapshot.child("Location").getValue(String.class));
                p_title.setText(title);
                problem.Picture_Id=0;
                s=dataSnapshot.child("Status").getValue(String.class);
                if(s=="true")
                {
                    p_status.setText("Solved");
                }
                else
                {
                    p_status.setText("Not Solved");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void onClickChangeStatus(View view)
    {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database1.getReference("Problems");
        DatabaseReference pid1= myRef.child(title);
        String s1= (String) p_status.getText();
        if(s1.equals("Solved"))
        {
            p_status.setText("Not Solved");
            pid1.child("Status").setValue("false");
        }
        else if(s1.equals("Not Solved"))
        {
            p_status.setText("Solved");
            pid1.child("Status").setValue("true");
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent= new Intent(CheckStatus.this, LogInActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.report1) {
            Intent intent= new Intent(CheckStatus.this, ReportActivity1.class);
            startActivity(intent);
        } else if (id == R.id.status1) {
            Intent intent= new Intent(CheckStatus.this, StatusActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            Intent intent= new Intent(CheckStatus.this, HomeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
