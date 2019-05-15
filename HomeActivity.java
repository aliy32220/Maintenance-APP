package com.example.ali.fastmainappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity{
    Button signup_btn;
    ImageButton login_btn;
    EditText username_text,password_text;
    String Username,Password, Email;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.home_page);
        signup_btn =(Button) findViewById(R.id.signup_button);
        login_btn = (ImageButton) findViewById(R.id.login_button);
        username_text = (EditText) findViewById(R.id.username_text);
        password_text = (EditText) findViewById(R.id.password_text);
    }
    public void onClickLogInBtn(View v)
    {
        Email= String.valueOf(username_text.getText());
        Password=  String.valueOf(password_text.getText());
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

    }
    public void onClickSignUpBtn(View v)
    {
        Intent i1 = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(i1);
    }
}

