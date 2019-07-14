package com.example.s10179502_week11practical;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserActivity extends AppCompatActivity {
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        db = new DbHandler(this,null,null,1 );
    }

    public void onCancel(View v){
        Intent in = new Intent(CreateUserActivity.this,MainActivity.class);
        startActivity(in);
    }

    public void onCreate(View v){
        EditText etUser = findViewById(R.id.etUsername);
        EditText etPass = findViewById(R.id.etPassword);

        String txtUser = etUser.getText().toString();
        String txtPass = etPass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(txtUser);

        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$");
        Matcher passMatcher = passPattern.matcher(txtPass);



        if(userMatcher.matches() && passMatcher.matches()){
            SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS",MODE_PRIVATE).edit();
            editor.putString("Username",txtUser);
            editor.putString("Password",txtPass);
            Account a = new Account(txtUser,txtPass);
            db.addAccount(a);
            Toast tt = Toast.makeText(CreateUserActivity.this,"New User Created Successfully", Toast.LENGTH_LONG);
            tt.show();
        }
        else{
            Toast tt = Toast.makeText(CreateUserActivity.this,"Invalid User Creation. Please Try Again", Toast.LENGTH_LONG);
            tt.show();
        }
    }
}
