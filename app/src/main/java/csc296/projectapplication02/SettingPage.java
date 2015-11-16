package csc296.projectapplication02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc296.projectapplication02.model.User;
import csc296.projectapplication02.model.UserCollection;

public class SettingPage extends AppCompatActivity
{
    EditText mName, mBirthday, mHometown, mBio;
    Button mSubmit;

    String mEmail;

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    UserCollection mCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        mName = (EditText) findViewById(R.id.setting_name);
        mBio = (EditText) findViewById(R.id.setting_bio);
        mBirthday = (EditText) findViewById(R.id.setting_birthday);
        mSubmit = (Button) findViewById(R.id.setting_submit);
        mHometown = (EditText) findViewById(R.id.setting_hometown);

        Intent i = getIntent();
        mEmail = i.getStringExtra(EMAIL_KEY);

        mCollection = UserCollection.get(getApplicationContext());

        mName.setText(mCollection.getUser(mEmail).getFullName());
        mBio.setText(mCollection.getUser(mEmail).getBio());
        mBirthday.setText(mCollection.getUser(mEmail).getBirthday());
        mHometown.setText(mCollection.getUser(mEmail).getHomeTown());

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String bio = mBio.getText().toString();
                String birthday = mBirthday.getText().toString();
                String home = mHometown.getText().toString();

                User u = mCollection.getUser(mEmail);
                u.setHomeTown(home);
                u.setFullName(name);
                u.setBio(bio);
                if(!mBirthday.getText().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
                {
                    Toast.makeText(getApplicationContext(),
                            "Invalid birthday input. Birthday should match the format: dd/mm/yyyy",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    u.setBirthday(birthday);
                }

                mCollection.updateUser(u);

                Toast.makeText(getApplicationContext(),
                        "Successfully updated your profile",
                        Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.setting_menu_my_posting:
            {
                Intent i = new Intent(this,CreatePostPage.class);
                i.putExtra(EMAIL_KEY, mEmail);
                startActivity(i);
                return true;
            }

            case R.id.setting_menu_my_log_out:
            {
                Intent i = new Intent(this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                return true;
            }

            case R.id.setting_menu_home:
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
