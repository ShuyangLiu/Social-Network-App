package csc296.projectapplication02.model;


import csc296.projectapplication02.R;

public class User
{

    private String mEmail;
    private String mPassword;
    private String mBirthday;
    private String mFullName;
    private String mProfilePicture;
    private String mHomeTown;
    private String mBio;

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

        //default bio is a message showing there is not bio yet
        //The user can write a short BIO in the profile setting page
        mBio = "You haven't write your BIO yet...\n" +
                "Get started by editing it in profile setting!";

        //default hometown
        //The user can edit it in the profile setting page
        mHomeTown = "Earth";

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

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public String getHomeTown() {
        return mHomeTown;
    }

    public void setHomeTown(String homeTown) {
        mHomeTown = homeTown;
    }

    @Override
    public String toString() {
        return "User{" +
                "Email='" + mEmail + '\n' +
                ", Password='" + mPassword + '\n' +
                ", Birthday='" + mBirthday + '\n' +
                ", FullName='" + mFullName + '\n' +
                ", ProfilePicture='" + mProfilePicture + '\n' +
                ", HomeTown='" + mHomeTown + '\n' +
                ", Bio='" + mBio + '\n' +
                '}';
    }
}
