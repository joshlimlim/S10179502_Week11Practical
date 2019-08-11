package com.example.s10179502_week11practical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    DbHandler db = new DbHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvNewUser = findViewById(R.id.tvNewUser);

        tvNewUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent in = new Intent(MainActivity.this, CreateUserActivity.class);
                    startActivity(in);
                    return true;
                }
                return false;
            }
        });
    }

    public void onLogin(View v) {
        EditText etUser = findViewById(R.id.etCreateUsername);
        EditText etPass = findViewById(R.id.etCreatePassword);

        String txtUser = etUser.getText().toString();
        String txtPass = etPass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(txtUser);

        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$");
        Matcher passMatcher = passPattern.matcher(txtPass);

        if (userMatcher.matches() && passMatcher.matches()) {
            /*SharedPreferences sharedPref = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE);
            String user = sharedPref.getString("Username", "");
            String pass = sharedPref.getString("Password", "");
            txtUser.equals(user) && txtPass.equals(pass)*/
            if (db.checkLogin(txtUser,txtPass,this)) {
                SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS",MODE_PRIVATE).edit();
                editor.putString("Username",txtUser);
                editor.putString("Password",txtPass);
                editor.apply();
                Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_LONG).show();
                Intent in = new Intent(MainActivity.this,AccountsActivity.class);
                in.putExtra("Username",txtUser);
                in.putExtra("Password",txtPass);
                startActivity(in);
            }
        } else {
            Toast tt = Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG);
            tt.show();
        }
    }
}
