package csc296.projectapplication02.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc296.projectapplication02.model.User;


public class Cursor_Wrapper extends CursorWrapper
{

    public Cursor_Wrapper(Cursor cursor)
    {
        super(cursor);
    }

    public User getUser()
    {

        String email = getString(getColumnIndex(Schema.Users.Cols.EMAIL));
        String password = getString(getColumnIndex(Schema.Users.Cols.PASSWORD));
        String birthday = getString(getColumnIndex(Schema.Users.Cols.BIRTH_DATE));
        String fullName = getString(getColumnIndex(Schema.Users.Cols.FULL_NAME));
        String photoPath = getString(getColumnIndex(Schema.Users.Cols.PROFILE_PIC));

        User user = new User(email,password,birthday,fullName);
        user.setProfilePicture(photoPath);

        return user;

    }




}
