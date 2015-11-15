package csc296.projectapplication02;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc296.projectapplication02.model.UserCollection;

public class LoginPage extends AppCompatActivity
{

    private EditText mEmail;
    private EditText mPassword;
    private UserCollection mCollection;

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    public static final String TAG = "Project02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mCollection = UserCollection.get(getApplicationContext());

        TextView title = (TextView) findViewById(R.id.loginPage_title);
        mEmail = (EditText) findViewById(R.id.loginPage_editText_email);
        mPassword = (EditText) findViewById(R.id.loginPage_editText_password);
        Button loginButton = (Button) findViewById(R.id.loginPage_button_login);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        title.setTypeface(raleway);
        mEmail.setTypeface(raleway);
        mPassword.setTypeface(raleway);
        loginButton.setTypeface(raleway);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check the email and password and log in

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (mCollection.checkUserExist(email)) {
                    if (mCollection.passwordCorrect(email, password)) {
                        /*successfully logged in*/
                        Intent intent = new Intent(LoginPage.this,HomePage.class);
                        intent.putExtra(EMAIL_KEY,email);
                        startActivity(intent);
                        Log.i(TAG,"[LoginPage]: successfully logged in");
                    } else {
                        /*wrong password*/
                        Toast.makeText(getApplicationContext(),
                                "Incorrect Password. Please try again",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    /*email not found*/
                    Toast.makeText(getApplicationContext(),
                            "Email not exist. Please sign up",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
