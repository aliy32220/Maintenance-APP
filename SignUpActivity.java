package com.example.ali.fastmainappfinal;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    Button Register_btn;
    EditText Name, Email_text,PhoneNumber_text,Password_text,ConfirmPassword_text;
    CheckBox checkBox1;
    RadioButton RadioBtn1,RadioBtn2;
    String name1, email,phone_number,password,confirm_password;
    Boolean checkBoxState,radioBtn_male=false,radioBtn_female=false;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseAuth Auth;
    public static  int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        Auth = FirebaseAuth.getInstance();
        Register_btn = (Button) findViewById(R.id.register_button);
        Name = (EditText) findViewById(R.id.name1);
        Email_text = (EditText) findViewById(R.id.singup_email);
        PhoneNumber_text = (EditText) findViewById(R.id.singup_phonenumber);
        Password_text = (EditText) findViewById(R.id.signup_password1);
        ConfirmPassword_text = (EditText) findViewById(R.id.signup_password2);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        RadioBtn1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioBtn2 = (RadioButton) findViewById(R.id.radioButton2);
    }
    public void onClickCheckBox(View v)
    {
        boolean Checked = ((CheckBox) v).isChecked();
        if (Checked)
        {
            checkBoxState=true;
        }
        else
        {
            checkBoxState=false;
        }
    }
    public void onRadioButtonClick(View v)
    {
        boolean Checked1 = ((RadioButton)v).isChecked();
        //Checking which Radio Button is Clicked
        switch(v.getId())
        {
            case R.id.radioButton1:
                if(Checked1)
                    radioBtn_male=true;
                break;
            case R.id.radioButton2:
                if(Checked1)
                    radioBtn_female=true;
                break;
        }
    }
    public void onClickRegisterBtn(View v)
    {
        count = count +1;
        name1=String.valueOf(Name.getText());
        email=String.valueOf(Email_text.getText());
        phone_number=String.valueOf(PhoneNumber_text.getText());
        password=String.valueOf(Password_text.getText());
        confirm_password=String.valueOf(ConfirmPassword_text.getText());

        if(password.equals(confirm_password) && checkBoxState==true && (radioBtn_male==true || radioBtn_female==true)  ) {

            Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i2= new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(i2);
                        finish();
                    }
                }
            });
        }
        else{
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            if (password.equals(confirm_password) == false)
            {
                dlgAlert.setMessage("Passwords Doesnt Match");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else if (checkBoxState==false)
            {
                dlgAlert.setMessage("Check box not Checked");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else if (radioBtn_female != true && radioBtn_male != true)
            {
                dlgAlert.setMessage("Select Gender");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
    }
}
