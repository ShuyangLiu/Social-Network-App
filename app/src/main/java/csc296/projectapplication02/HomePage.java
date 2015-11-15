package csc296.projectapplication02;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Typeface raleway = Typeface.createFromAsset(getAssets(),
                "raleway/Raleway-Regular.ttf");

        TextView testText = (TextView) findViewById(R.id.homePage_testText);
        Intent intent = getIntent();
        String email = intent.getStringExtra(EMAIL_KEY);

        String msg = "Current user email: " + email;
        testText.setText(msg);
        testText.setTypeface(raleway);
    }


}
