package csc296.projectapplication02;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import csc296.projectapplication02.model.User;
import csc296.projectapplication02.model.UserCollection;

public class MyProfile extends AppCompatActivity
{

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    public static final String TAG = "Project02";

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FORESLASH = "/";

    String mEmail;
    UserCollection mCollection;

    TextView mName;
    TextView mBio;
    TextView mHometown;
    TextView mBirthday;
    ImageView mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent i = getIntent();
        mEmail = i.getStringExtra(EMAIL_KEY);
        mCollection = UserCollection.get(getApplicationContext());

        mName = (TextView) findViewById(R.id.myprofile_Name);
        mBio = (TextView) findViewById(R.id.myprofile_bio);
        mBirthday = (TextView) findViewById(R.id.myprofile_birthday);
        mHometown = (TextView) findViewById(R.id.myprofile_hometown);
        mPhoto = (ImageView) findViewById(R.id.myprofile_Photo);

        User u = mCollection.getUser(mEmail);
        mName.setText(u.getFullName());
        mBio.setText(u.getBio());
        mBirthday.setText(u.getBirthday());
        mHometown.setText(u.getHomeTown());

//        Context context = getApplicationContext();

//        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
//                context.getResources().getResourcePackageName(R.drawable.ic_face_black_24dp) + '/' +
//                context.getResources().getResourceTypeName(R.drawable.ic_face_black_24dp) + '/' +
//                context.getResources().getResourceEntryName(R.drawable.ic_face_black_24dp) );
//        Bitmap p = getScaledBitmap(uri.getPath(),200,200);
//        mPhoto.setImageBitmap(p);


        mPhoto.setImageResource(R.drawable.bmo);

    }

    public static Bitmap getScaledBitmap(String path, Activity activity)
    {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaledBitmap(path, size.x, size.y);
    }

    public static Bitmap getScaledBitmap(String path, int width, int height)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        Log.d(TAG, "width=" + width + "," + "height=" + height);
        Log.d(TAG, "srcWidth=" + srcWidth + "," + "srcHeight=" + srcHeight);

        int sampleSize = 1;
        if(srcHeight > height || srcWidth > width ) {
            if(srcWidth > srcHeight) {
                sampleSize = Math.round(srcHeight / height);
            }
            else {
                sampleSize = Math.round(srcWidth / width);
            }
        }

        Log.d(TAG, "sampleSize=" + sampleSize);

        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;

        return BitmapFactory.decodeFile(path, scaledOptions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.my_profile_menu_my_posting:
            {
                Intent i = new Intent(this,CreatePostPage.class);
                i.putExtra(EMAIL_KEY, mEmail);
                startActivity(i);
                return true;
            }

            case R.id.my_profile_menu_my_log_out:
            {
                Intent i = new Intent(this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                return true;
            }

            case R.id.my_profile_menu_my_settings:
            {
                Intent i = new Intent(this,SettingPage.class);
                i.putExtra(EMAIL_KEY, mEmail);
                startActivity(i);
                return true;
            }

            case R.id.my_profile_menu_home:
            {
                Intent i = new Intent(this,HomePage.class);
                i.putExtra(EMAIL_KEY,mEmail);
                startActivity(i);
                return true;
            }
        }


        return super.onOptionsItemSelected(item);
    }

}
