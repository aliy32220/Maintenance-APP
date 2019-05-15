package com.example.ali.fastmainappfinal;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<String> Titles = new ArrayList<String>();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference problemRef=databaseReference.child("Problems");
    ListView listView;
    TextView T1;
    String selected_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        listView = (ListView) findViewById(R.id.listView);
        T1=(TextView)findViewById(R.id.TEXT);
        problemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot problemSnapshot:dataSnapshot.getChildren())
                {
                    String data=problemSnapshot.getKey();
                    Titles.add(data);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        MyAdapter myAdapter=new MyAdapter(this,R.layout.listviewfile,Titles);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_title=Titles.get(position);
                Intent i=new Intent(StatusActivity.this, CheckStatus.class);
                i.putExtra("TITLE",selected_title);
                startActivity(i);
            }
        });
        listView.setAdapter(myAdapter);
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
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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
        getMenuInflater().inflate(R.menu.status, menu);
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
            Intent intent= new Intent(StatusActivity.this, LogInActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.report1) {
            Intent intent= new Intent(StatusActivity.this, ReportActivity1.class);
            startActivity(intent);
        } else if (id == R.id.status1) {
            Intent intent= new Intent(StatusActivity.this, StatusActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            Intent intent= new Intent(StatusActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
