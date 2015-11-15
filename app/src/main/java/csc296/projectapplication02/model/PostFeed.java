package csc296.projectapplication02.model;


import java.util.Calendar;

public class PostFeed
{

    private String mEmail;
    private Calendar mTimePosted;
    private String mPhotoPath;
    private String mContent;

    public PostFeed(String email, long timePosted, String photoPath,
                    String content)
    {
        mEmail = email;

        /*
            * Save the milliseconds from epoch representation of the calendar:
            *
            * long millis = calendar.getTimeInMillis();
            *
            * Then store this long in the db. You can recover the calendar by reading
            * the long value from the db and using
            *
            * setTimeInMillis(long millis)
            *
            * in the
            * calendar.
            *
        */

        mTimePosted = Calendar.getInstance();
        mTimePosted.setTimeInMillis(timePosted);

        mPhotoPath = photoPath;
        mContent = content;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Calendar getTimePosted() {
        return mTimePosted;
    }

    public void setTimePosted(long timePosted) {
        mTimePosted.setTimeInMillis(timePosted);

    }

    public String getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        mPhotoPath = photoPath;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return "PostFeed{" +
                "Content='" + mContent + '\n' +
                ", Email='" + mEmail + '\n' +
                ", TimePosted=" + mTimePosted + '\n' +
                ", PhotoPath='" + mPhotoPath + '\n' +
                '}';
    }
}
