package com.example.s10179502_week11practical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        ArrayList<Account> data = new ArrayList<>();

        DbHandler db = new DbHandler(this);
        ArrayList<Account> records = db.getAll();
        for(Account a:records){
            data.add(a);
        }

        AccountAdapter adapter = new AccountAdapter(this,R.layout.layout_account,data);

        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
