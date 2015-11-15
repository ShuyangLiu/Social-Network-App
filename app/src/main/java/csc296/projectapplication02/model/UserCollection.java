package csc296.projectapplication02.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import csc296.projectapplication02.database.Cursor_Wrapper;
import csc296.projectapplication02.database.OpenHelper;
import csc296.projectapplication02.database.Schema;

public class UserCollection
{
    private final SQLiteDatabase mDatabase;
    public static UserCollection sCollection;
    private Map<String,User> mHashMap;

    public static final String TAG = "Project02";

    /*constructor*/
    public UserCollection(Context context)
    {
        Context context1 = context.getApplicationContext();
        mDatabase = new OpenHelper(context1).getWritableDatabase();
        mHashMap = getHashMap();
    }

    /*getter method*/
    public static UserCollection get (Context context)
    {
        if (sCollection==null)
        {
            sCollection = new UserCollection(context);
        }
        return sCollection;
    }

    /*adding a new user to the database*/
    public void addUser(User user)
    {
        ContentValues values = getContentValues_user(user);
        mDatabase.insert(Schema.Users.NAME, null, values);
        Log.i(TAG,"[UserCollection]: User successfully added to the database!\n" +
        user.toString());
    }

    private static ContentValues getContentValues_user(User user)
    {
        ContentValues values = new ContentValues();

        values.put(Schema.Users.Cols.EMAIL,user.getEmail());
        values.put(Schema.Users.Cols.PASSWORD,user.getPassword());
        values.put(Schema.Users.Cols.BIRTH_DATE,user.getBirthday());
        values.put(Schema.Users.Cols.FULL_NAME, user.getFullName());
        values.put(Schema.Users.Cols.PROFILE_PIC, user.getProfilePicture());

        return values;
    }

    /*check to see if the user email is already in the database*/
    public boolean checkUserExist(String email)
    {
        User user = mHashMap.get(email);
        return user != null;
    }

    public boolean passwordCorrect(String email,String password)
    {
        User user = mHashMap.get(email);
        return password.equals(user.getPassword());
    }

    /*helper method to return a hash map according to the database*/
    private Map<String,User> getHashMap()
    {
        Map<String,User> hashMap = new HashMap<>();

        try (Cursor_Wrapper cursor_wrapper = query_user(null, null)) {
            cursor_wrapper.moveToFirst();
            while (!cursor_wrapper.isAfterLast()) {
                User user = cursor_wrapper.getUser();
                hashMap.put(user.getEmail(), user);
                cursor_wrapper.moveToNext();
            }
            cursor_wrapper.close();
        }
        return hashMap;
    }

    /*Helper method to query users*/
    private Cursor_Wrapper query_user(String where,String arg[])
    {
        Cursor cursor = mDatabase.query(Schema.Users.NAME,
                null,where,arg,null,null,null);

        return new Cursor_Wrapper(cursor);
    }

}
