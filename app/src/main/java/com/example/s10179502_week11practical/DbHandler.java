package com.example.s10179502_week11practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "accounts.db";
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addAccount(Account a) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, a.getUsername());
        contentValues.put(COLUMN_PASSWORD, a.getPassword());

        long result = database.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public ArrayList<Account> getAll() {
        ArrayList<Account> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,
                null);

        while(c.moveToNext())
        {
            Account a = new Account();
            a.setUsername(c.getString(1));
            a.setPassword(c.getString(2));

            list.add(a);
        }

        c.close();
        db.close();
        return list;
    }

    public boolean checkLogin(String username, String password, Context context) {
        String[] columns = {COLUMN_ID};
        SQLiteDatabase database = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + " =?" + " AND " + COLUMN_PASSWORD + " =?";
        String[] selectionArgs = {username, password};
        Cursor result = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        //Toast.makeText(context, result.getString(1), Toast.LENGTH_SHORT).show();

        return result.getCount() > 0;
    }
}
