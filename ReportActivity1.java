package com.example.ali.fastmainappfinal;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportActivity1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    public DatabaseReference probRef= mDatabase.child("Problems");
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    EditText E_T, E_PD, E__L;
    ImageView myImage;
    String Title, Problem_Description, Location;
    boolean Status=false;
    int Picture_id;
    public static int P_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1);
        E_T=(EditText) findViewById(R.id.problemTitle);
        E_PD=(EditText) findViewById(R.id.problem_Description);
        E__L=(EditText) findViewById(R.id.problem_location);
        myImage=(ImageView) findViewById(R.id.myImage);

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
    public void onClickAddImageBtn(View view)
    {
       /* Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);*/
    }
    public void onClickSubmitProblemBtn(View view)
    {
        Title= String.valueOf(E_T.getText());
        Problem_Description= String.valueOf((E_PD.getText()));
        Location = String.valueOf(E__L.getText());

        if(myImage.getId()!= 0)
        {
            Picture_id = myImage.getId();
        }
        else{
            Picture_id=0;
        }
        P_id++;
        Problem problem= new Problem(Title, Problem_Description, Location, Picture_id,Status);
        probRef.child(Title);
        probRef.child(Title).child("Problem Description").setValue(problem.Problem_Description);
        probRef.child(Title).child("Location").setValue(problem.Location);
        probRef.child(Title).child("Picture ID").setValue(String.valueOf(problem.Picture_Id));
        probRef.child(Title).child("Status").setValue("false");
        Intent intent= new Intent(ReportActivity1.this, LogInActivity.class);
        startActivity(intent);
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
        getMenuInflater().inflate(R.menu.report_activity1, menu);
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
            Intent intent= new Intent(ReportActivity1.this, LogInActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.report1) {
            Intent intent= new Intent(ReportActivity1.this, ReportActivity1.class);
            startActivity(intent);
        } else if (id == R.id.status1) {
            Intent intent= new Intent(ReportActivity1.this, StatusActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            Intent intent= new Intent(ReportActivity1.this, HomeActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
