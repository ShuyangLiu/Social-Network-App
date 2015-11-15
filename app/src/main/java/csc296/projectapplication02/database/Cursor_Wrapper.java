package csc296.projectapplication02.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc296.projectapplication02.model.PostFeed;
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
        String bio = getString(getColumnIndex(Schema.Users.Cols.BIO));
        String hometown = getString(getColumnIndex(Schema.Users.Cols.HOMETOWN));

        User user = new User(email,password,birthday,fullName);
        user.setProfilePicture(photoPath);
        user.setBio(bio);
        user.setHomeTown(hometown);

        return user;
    }

    public String getFavUser()
    {
        return getString(getColumnIndex(Schema.Favorites.Cols.FAVORITE));
    }

    public PostFeed getPost()
    {
        String email = getString(getColumnIndex(Schema.FeedItems.Cols.EMAIL));
        long timePosted = getLong(getColumnIndex(Schema.FeedItems.Cols.POSTED_DATE));
        String content = getString(getColumnIndex(Schema.FeedItems.Cols.CONTENT));
        String photo_path = getString(getColumnIndex(Schema.FeedItems.Cols.PHOTO_PATH));

        return new PostFeed(email,timePosted,photo_path,content);
    }




}
