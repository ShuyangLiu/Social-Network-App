package csc296.projectapplication02.database;


public class Schema
{
    public static final String DATABASE_NAME = "project02.db";
    public static final int VERSION = 1;

    public static class Users
    {
        public static final String NAME = "users";
        public static class Cols
        {
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
            public static final String FULL_NAME = "full_name";
            public static final String BIRTH_DATE = "birth_date";
            public static final String BIO = "biography";
            public static final String HOMETOWN = "hometown";
            public static final String PROFILE_PIC = "profile_pic";
        }
    }

    public static class FeedItems
    {
        public static final String NAME = "feed_items";
        public static class Cols
        {
            public static final String EMAIL = "email";//who post it
            public static final String POSTED_DATE = "posted_date";//when it is posted
            public static final String CONTENT = "content";//what is posted
            public static final String PHOTO_PATH = "photo_path";//a photo
        }
    }


    /*
    *    Email   |   Favorite
    * --------------------------
    * a@email.com|  b@email.com
    * a@email.com|  c@email.com
    * a@email.com|  d@email.com
    * b@email.com|  a@email.com
    *
    * */
    public static class Favorites
    {
        public static final String NAME = "favorites";
        public static class Cols
        {
            public static final String EMAIL = "email";
            public static final String FAVORITE = "favorite";
        }
    }


}
