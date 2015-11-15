package csc296.projectapplication02.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import csc296.projectapplication02.database.OpenHelper;
import csc296.projectapplication02.database.Schema;

public class UserCollection
{
    private final Context mContext;
    private final SQLiteDatabase mDatabase;
    public static UserCollection sCollection;

    public static final String TAG = "Project02";

    /*constructor*/
    public UserCollection(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new OpenHelper(mContext).getWritableDatabase();
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
        ContentValues values = getContentValues(user);
        mDatabase.insert(Schema.Users.NAME,null,values);
        Log.i(TAG,"[UserCollection]: User successfully added to the database!");
    }

    private static ContentValues getContentValues(User user)
    {
        ContentValues values = new ContentValues();

        values.put(Schema.Users.Cols.EMAIL,user.getEmail());
        values.put(Schema.Users.Cols.PASSWORD,user.getPassword());
        values.put(Schema.Users.Cols.BIRTH_DATE,user.getBirthday());
        values.put(Schema.Users.Cols.FULL_NAME,user.getFullName());
        values.put(Schema.Users.Cols.PROFILE_PIC,user.getProfilePicture());

        return values;
    }






}
