package com.example.qlnhatro.Service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserSession {
    //Variables
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;
    private static final String IS_LOGIN = "isLoggedIn";
    public static final String KEY_USERID = "userID";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String TIME_EXPIRED = "expired";

    // Constructor
    public UserSession(Context _context)
    {
        context = _context;
        userSession = _context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor= userSession.edit();
    }
    // Create login session
    public void createLoginSession(int userId,String username,String fullname,String phone,String address)
    {
        editor.putBoolean(IS_LOGIN, true);
        // User Info
        editor.putString(KEY_USERNAME, username);
        editor.putInt(KEY_USERID, userId);
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_ADDRESS, address);
        // Time Expired
        editor.putLong(TIME_EXPIRED, System.currentTimeMillis() + 864000000); // 10day * 24h * 60m * 60s * 1000mili =
        // Commit
        editor.commit();
    }
    // Get user detail
    public HashMap<String, String> getUsersDetailFromSession()
    {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(KEY_USERNAME, userSession.getString(KEY_USERNAME, null));
        userData.put(KEY_USERID, userSession.getString(KEY_USERID, null));
        userData.put(KEY_FULLNAME, userSession.getString(KEY_FULLNAME, null));
        userData.put(KEY_PHONE, userSession.getString(KEY_PHONE, null));
        userData.put(TIME_EXPIRED, userSession.getString(TIME_EXPIRED, null));
        return userData;
    }
    // Check login
    public boolean checkLogin()
    {
        if(userSession.getBoolean(IS_LOGIN, true))
        {
            if(userSession.getLong(TIME_EXPIRED,0) < System.currentTimeMillis())
            {
                logoutSession();
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    //Logout
    public void logoutSession()
    {
        editor.clear();
        editor.commit();
    }
}
