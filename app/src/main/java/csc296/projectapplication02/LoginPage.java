package csc296.projectapplication02;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView mTitle = (TextView) findViewById(R.id.loginPage_title);
        EditText mEmail = (EditText) findViewById(R.id.loginPage_editText_email);
        EditText mPassword = (EditText) findViewById(R.id.loginPage_editText_password);
        Button mLoginButton = (Button) findViewById(R.id.loginPage_button_login);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        mTitle.setTypeface(raleway);
        mEmail.setTypeface(raleway);
        mPassword.setTypeface(raleway);
        mLoginButton.setTypeface(raleway);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check the email and password and log in
            }
        });
    }
}
