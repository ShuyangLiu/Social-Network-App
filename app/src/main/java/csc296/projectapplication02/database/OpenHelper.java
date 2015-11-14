package csc296.projectapplication02.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper
{

    /*constructor*/
    public OpenHelper(Context context , String name ,
                      SQLiteDatabase.CursorFactory factory , int version)
    {
        super(context , name , factory , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
