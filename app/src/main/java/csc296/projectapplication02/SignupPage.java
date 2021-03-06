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

import csc296.projectapplication02.model.User;
import csc296.projectapplication02.model.UserCollection;

public class SignupPage extends AppCompatActivity
{
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPassword_c;
    private EditText mUsername;
    private EditText mBirthday;

    private UserCollection mCollection;

    public static final String TAG = "Project02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        mCollection = UserCollection.get(getApplicationContext());

        TextView title = (TextView) findViewById(R.id.signupPage_title);

        mEmail = (EditText) findViewById(R.id.signupPage_editText_email);
        mPassword = (EditText) findViewById(R.id.signupPage_editText_password);
        mPassword_c = (EditText) findViewById(R.id.signupPage_editText_password_c);
        mUsername = (EditText) findViewById(R.id.signupPage_editText_username);
        mBirthday = (EditText) findViewById(R.id.signupPage_editText_birthday);

        Button button = (Button) findViewById(R.id.signupPage_button_signup);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        title.setTypeface(raleway);
        mEmail.setTypeface(raleway);
        mPassword.setTypeface(raleway);
        mPassword_c.setTypeface(raleway);
        mUsername.setTypeface(raleway);
        button.setTypeface(raleway);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String password_c = mPassword_c.getText().toString();
                String birthday = mBirthday.getText().toString();
                String username = mUsername.getText().toString();

                if(mEmail.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Email should not be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(mUsername.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Username should not be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(mPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Password should not be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(mPassword_c.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Confirmed Password should not be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(mBirthday.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Birthday should not be empty!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!mBirthday.getText().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
                {
                    Toast.makeText(getApplicationContext(),
                            "Invalid birthday input. Birthday should match the format: dd/mm/yyyy",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if (password.equals(password_c)) {
                        if (mCollection.checkUserExist(email)) {
                            Toast.makeText(getApplicationContext(),
                                    "This email is already registered! " +
                                            "Please log in or use another email to register",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(email, password, birthday, username);
                            /*add the user to the database*/
                            mCollection.addUser(user);
                            /*reset the edit text field*/
                            mEmail.setText("");
                            mPassword.setText("");
                            mBirthday.setText("");
                            mPassword_c.setText("");
                            mUsername.setText("");
                            Intent intent = new Intent(SignupPage.this, LoginPage.class);
                            startActivity(intent);
                            Log.i(TAG, "[SignupPage]: successfully created a new account");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Your password and confirmed password are not the same! " +
                                        "Please check and submit again!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
