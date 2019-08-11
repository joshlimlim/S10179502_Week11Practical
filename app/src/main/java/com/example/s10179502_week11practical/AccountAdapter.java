package com.example.s10179502_week11practical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> {
    Context c;
    int layout;
    ArrayList<Account> data;

    public AccountAdapter(Context c,int layout, ArrayList<Account> data){
        super(c,layout,data);

        this.c = c;
        this.layout = layout;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View accountView = convertView;
        if(accountView == null){
            accountView = LayoutInflater.from(c).inflate(layout,parent,false);
        }

        TextView username = accountView.findViewById(R.id.account);
        TextView password = accountView.findViewById(R.id.password);

        Account a = data.get(position);
        username.setText(a.getUsername());
        password.setText(a.getPassword());
        return accountView;
    }
}
