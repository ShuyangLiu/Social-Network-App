package csc296.projectapplication02;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        Intent intent = getIntent();
        mEmail = intent.getStringExtra(EMAIL_KEY);
        Bundle bundle = new Bundle();
        bundle.putString(EMAIL_KEY,mEmail);

        /*
        TextView testText = (TextView) findViewById(R.id.homePage_testText);
        String msg = "Current user email: " + email;
        testText.setText(msg);
        testText.setTypeface(raleway);
        */

        HomePage_fragment f = new HomePage_fragment();
        f.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.homePage_recycler_view_frame, f)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.home_menu_my_posting:
            {
                Intent i = new Intent(this,CreatePostPage.class);
                i.putExtra(EMAIL_KEY, mEmail);
                startActivity(i);
                return true;
            }

            case R.id.home_menu_my_log_out:
            {
                Intent i = new Intent(this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                return true;
            }

            case R.id.home_menu_my_profile:
            {
                Intent i = new Intent(this,MyProfile.class);
                i.putExtra(EMAIL_KEY,mEmail);
                startActivity(i);
                return true;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
