package csc296.projectapplication02;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        TextView mTitle = (TextView) findViewById(R.id.signupPage_title);
        EditText mEmail = (EditText) findViewById(R.id.signupPage_editText_email);
        EditText mPassword = (EditText) findViewById(R.id.signupPage_editText_password);
        EditText mPassword_c = (EditText) findViewById(R.id.signupPage_editText_password_c);
        EditText mUsername = (EditText) findViewById(R.id.signupPage_editText_username);
        Button mButton = (Button) findViewById(R.id.signupPage_button_signup);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        mTitle.setTypeface(raleway);
        mEmail.setTypeface(raleway);
        mPassword.setTypeface(raleway);
        mPassword_c.setTypeface(raleway);
        mUsername.setTypeface(raleway);
        mButton.setTypeface(raleway);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add a new user to the database!
            }
        });

    }
}
