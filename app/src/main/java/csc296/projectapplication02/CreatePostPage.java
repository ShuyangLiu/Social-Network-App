package csc296.projectapplication02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import csc296.projectapplication02.model.PostFeed;
import csc296.projectapplication02.model.UserCollection;

public class CreatePostPage extends AppCompatActivity
{

    Button mSubmit;
    EditText mContent;
    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    String mEmail;
    UserCollection mCollection;

    PostFeed p;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_page);
        Intent i = getIntent();
        mEmail = i.getStringExtra(EMAIL_KEY);
        mContent = (EditText) findViewById(R.id.create_post_content);
        mSubmit = (Button) findViewById(R.id.create_post_submit);

        mCollection = UserCollection.get(getApplicationContext());

        c = Calendar.getInstance();

        p = new PostFeed(mEmail,
                c.getTimeInMillis(),
                null,
                mContent.getText().toString());

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setTimePosted(c.getTimeInMillis());
                mCollection.addPost(p);
            }
        });
    }


}
