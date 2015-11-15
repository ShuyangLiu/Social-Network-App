package csc296.projectapplication02.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Log.i(TAG, "[UserCollection]: User successfully added to the database!\n" +
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
        values.put(Schema.Users.Cols.HOMETOWN, user.getHomeTown());
        values.put(Schema.Users.Cols.BIO, user.getBio());

        return values;
    }

    /*add a new post to the database*/
    public void addPost(PostFeed p)
    {
        ContentValues values = getContentValues_post(p);
        mDatabase.insert(Schema.FeedItems.NAME,null,values);
        Log.i(TAG, "[UserCollection]: User successfully added to the database!\n" +
                p.toString());
    }

    private static ContentValues getContentValues_post(PostFeed postFeed)
    {
        ContentValues values = new ContentValues();

        values.put(Schema.FeedItems.Cols.EMAIL,postFeed.getEmail());
        values.put(Schema.FeedItems.Cols.POSTED_DATE,postFeed.getTimePosted().toString());
        values.put(Schema.FeedItems.Cols.CONTENT,postFeed.getContent());
        values.put(Schema.FeedItems.Cols.PHOTO_PATH,postFeed.getPhotoPath());

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
                null, where, arg, null, null, null);

        return new Cursor_Wrapper(cursor);
    }

    /*Helper method to query posts*/
    private  Cursor_Wrapper query_post(String where,String arg[])
    {
        Cursor cursor = mDatabase.query(Schema.FeedItems.NAME,
                null,where,arg,null,null,null);

        return new Cursor_Wrapper(cursor);
    }

    /*Helper method to query fav users*/
    private Cursor_Wrapper query_fav(String where,String arg[])
    {
        Cursor cursor = mDatabase.query(Schema.Favorites.NAME,
                null,where,arg,null,null,null);

        return new Cursor_Wrapper(cursor);
    }

    /*return a list of post of a specific user*/
    public List<PostFeed> getPost(String email)
    {
        List<PostFeed> post_feed = new ArrayList<>();
        String where = "email=?";
        String arg[] = new String[]{email};

        try(Cursor_Wrapper cursor_wrapper = query_post(where, arg))
        {
            cursor_wrapper.moveToFirst();
            while (!cursor_wrapper.isAfterLast()) {
                PostFeed post = cursor_wrapper.getPost();
                post_feed.add(post);
                cursor_wrapper.moveToNext();
            }
            cursor_wrapper.close();
        }

        return post_feed;

    }
    /*return a list of Favorite Users Email of given email*/
    public List<String> getFavUserEmails(String email)
    {
        List<String> fav_list = new ArrayList<>();
        String where = "email=?";
        String arg[] = new String[]{email};
        try(Cursor_Wrapper cursor_wrapper = query_fav(where, arg))
        {
            cursor_wrapper.moveToFirst();
            while (!cursor_wrapper.isAfterLast()) {
                String fav_email = cursor_wrapper.getFavUser();
                fav_list.add(fav_email);
                cursor_wrapper.moveToNext();
            }
            cursor_wrapper.close();
        }

        return fav_list;
    }

    /*return a list of post feeds according to the given email*/
    public List<PostFeed> getPostFeed(String email)
    {
        List<PostFeed> list = new ArrayList<>();

        List<String> email_list = getFavUserEmails(email);
        for(int i=0; i<email_list.size(); i++)
        {
            List<PostFeed> single_list = getPost(email_list.get(i));
            for(int j=0; j<single_list.size(); j++)
            {
                list.add(single_list.get(j));
            }
        }

        return list;
    }

    /*return a list of favorite users*/
    public List<User> getFavUsers(String email)
    {
        List<User> list = new ArrayList<>();

        List<String> email_list = getFavUserEmails(email);

        for(int i=0; i<email_list.size(); i++)
        {
            User user = mHashMap.get(email_list.get(i));
            list.add(user);
        }
        return list;
    }

    /*return a user according to given email*/
    public User getUser(String email)
    {
        return mHashMap.get(email);
    }

    /*return a list of all the users in the database*/
    public List<User> getAllUsers()
    {
        List<User> list = new ArrayList<>();

        try (Cursor_Wrapper cursor_wrapper = query_user(null, null)) {
            cursor_wrapper.moveToFirst();
            while (!cursor_wrapper.isAfterLast()) {
                User user = cursor_wrapper.getUser();
                list.add(user);
                cursor_wrapper.moveToNext();
            }
            cursor_wrapper.close();
        }

        return list;
    }


    /*return a list of unfavorited users*/
    public List<User> getUnfavUser(String email)
    {
        List<User> list = new ArrayList<>();
        List<User> fav_user = getFavUsers(email);
        List<User> all_user = getAllUsers();

        for(int i=0; i<all_user.size(); i++)
        {
            if(!fav_user.contains(all_user.get(i)))
            {
                list.add(all_user.get(i));
            }
        }
        return list;
    }






}
