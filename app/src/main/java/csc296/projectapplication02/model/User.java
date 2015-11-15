package csc296.projectapplication02.model;


import csc296.projectapplication02.R;

public class User
{

    private String mEmail , mPassword , mBirthday , mFullName , mProfilePicture;

    public User(String email, String password, String birthday,
                String fullName)
    {
        mEmail = email;

        mPassword = password;

        //For now it is only a string,
        // but I can make a date picker dialog if I have time
        mBirthday = birthday;

        mFullName = fullName;

        //default profile picture uri, use setter to change it to user photo
        mProfilePicture = "drawable://" + R.drawable.profile_filled;

    }

    @Override
    public String toString()
    {
        return "User{" +
                "mEmail='" + mEmail + '\n' +
                ", mPassword='" + mPassword + '\n' +
                ", mBirthday='" + mBirthday + '\n' +
                ", mFullName='" + mFullName + '\n' +
                ", mProfilePicture='" + mProfilePicture + '\n' +
                '}';
    }

    public String getEmail()
    {
        return mEmail;
    }

    public void setEmail(String email)
    {
        mEmail = email;
    }

    public String getPassword()
    {
        return mPassword;
    }

    public void setPassword(String password)
    {
        mPassword = password;
    }

    public String getBirthday()
    {
        return mBirthday;
    }

    public void setBirthday(String birthday)
    {
        mBirthday = birthday;
    }

    public String getFullName()
    {
        return mFullName;
    }

    public void setFullName(String fullName)
    {
        mFullName = fullName;
    }

    public String getProfilePicture()
    {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        mProfilePicture = profilePicture;
    }
}
