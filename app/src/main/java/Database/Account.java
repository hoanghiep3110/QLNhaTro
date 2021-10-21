package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import House.Accounts;




public class Account extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ApartmentManager";
    private static final int VERSION = 1;

    private static final String TB_ACCOUNT = "Account";
    private static final String ID_ACCOUNT = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";



    public Account(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS "+ TB_ACCOUNT +"("+ ID_ACCOUNT +" INTEGER  primary key AUTOINCREMENT,"+ NAME +" VARCHAR(255),"+ PHONE +" VARCHAR(10)," +
             ADDRESS +" VARCHAR (255),"+ USERNAME +" VARCHAR(255),"+ PASSWORD +" VARCHAR(255))";

    private String admin = "INSERT INTO "+TB_ACCOUNT+" VALUES (null,'admin','066999389','Q9','admin','123')";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TB_ACCOUNT;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
    }

    public Cursor getAllAccount() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TB_ACCOUNT, null);
        return cursor;
    }

    public void insertAccount(Accounts account) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, account.getName());
        values.put(PHONE, account.getPhone());
        values.put(ADDRESS, account.getAddress());
        values.put(USERNAME, account.getUserName());
        values.put(PASSWORD, account.getPassWord());
        db.insert(TB_ACCOUNT, null, values);
        db.close();
    }

    public boolean checkUser(String name, String pass) {
        // array of columns to fetch
        String[] columns = {
                ID_ACCOUNT
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USERNAME + " = ?" + " AND " + PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {name, pass};
        // query user table with conditions

        Cursor cursor = db.query(TB_ACCOUNT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
