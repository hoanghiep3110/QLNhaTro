package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import House.Accounts;
import java.util.ArrayList;
import java.util.List;




public class Account extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ApartmentManager";
    private static final int VERSION = 1;

    private static String TB_ACCOUNT = "account";
    private static String ID_ACCOUNT = "id";
    private static String NAME = "name";
    private static String PHONE = "phone";
    private static String ADDRESS = "address";
    private static String USERNAME = "username";
    private static String PASSWORD = "password";

    public Account(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    private String SQLQuery = "CREATE TABLE  account (ID_ACCOUNT integer primary key AUTOINCREMENT, NAME VARCHAR(255), PHONE VARCHAR(10), ADDRESS VARCHAR (255)," +
            "USERNAME VARCHAR(255),PASSWORD VARCHAR(255))";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public Cursor getAllAccount() {


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from TB_ACCOUNT", null);
        return cursor;
    }

    public void insertAccount(Accounts account){
        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO Accounts (id,name,address,phone,username,password) VALUES(null,?,?,?,?,?)",
//                new String[]{account.getAccountID()+"", account.getName()+"",account.getAddress()+"",
//                        account.getPhone()+"", account.getUserName()+"",account.getPassWord()});
        ContentValues values = new ContentValues();
        values.put(NAME,account.getName());
        values.put(PHONE,account.getPhone());
        values.put(ADDRESS,account.getAddress());
        values.put(USERNAME,account.getUserName());
        values.put(PASSWORD,account.getPassWord());
        db.insert(TB_ACCOUNT,null,values);
        db.close();
    }
}
