package csc296.projectapplication02;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mWelcome = (TextView) findViewById(R.id.mainActivity_welcome);
        Button mLogin = (Button) findViewById(R.id.mainActivity_loginButton);
        Button mSignup = (Button) findViewById(R.id.mainActivity_signupButton);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");
        mWelcome.setTypeface(raleway);
        mLogin.setTypeface(raleway);
        mSignup.setTypeface(raleway);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent);
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignupPage.class);
                startActivity(intent);
            }
        });

    }
}
