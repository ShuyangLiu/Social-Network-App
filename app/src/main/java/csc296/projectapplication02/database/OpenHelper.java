package csc296.projectapplication02.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import csc296.projectapplication02.MainActivity;

public class OpenHelper extends SQLiteOpenHelper
{

    public static final String TAG = "Project02";

    /*constructor*/
    public OpenHelper(Context context)
    {
        super(context, Schema.DATABASE_NAME, null, Schema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE " + Schema.Users.NAME
                        + "(_id integer primary key autoincrement, "
                        + Schema.Users.Cols.EMAIL + ", "
                        + Schema.Users.Cols.PASSWORD + ", "
                        + Schema.Users.Cols.FULL_NAME + ", "
                        + Schema.Users.Cols.BIRTH_DATE + ", "
                        + Schema.Users.Cols.BIO + ", "
                        +Schema.Users.Cols.HOMETOWN + ", "
                        + Schema.Users.Cols.PROFILE_PIC + ")"
        );

        Log.i(TAG, "[OpenHelper]: Table created: " + Schema.Users.NAME);

        db.execSQL("CREATE TABLE " + Schema.FeedItems.NAME
                        + "(_id integer primary key autoincrement, "
                        + Schema.FeedItems.Cols.EMAIL + ", "
                        + Schema.FeedItems.Cols.POSTED_DATE + ", "
                        + Schema.FeedItems.Cols.CONTENT + ", "
                        + Schema.FeedItems.Cols.PHOTO_PATH + ")"
        );

        Log.i(TAG, "[OpenHelper]: Table created: " + Schema.FeedItems.NAME);


        db.execSQL("CREATE TABLE " + Schema.Favorites.NAME
                        + "(_id integer primary key autoincrement, "
                        + Schema.Favorites.Cols.EMAIL + ", "
                        + Schema.Favorites.Cols.FAVORITE + ")"
        );

        Log.i(TAG, "[OpenHelper]: Table created: " + Schema.Favorites.NAME);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /*
        * For upgrading the database according to schema
        */
    }
}
