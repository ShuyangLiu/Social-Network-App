package csc296.projectapplication02;


import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import csc296.projectapplication02.model.PostFeed;

public class Recycler_holder extends RecyclerView.ViewHolder
{

    PostFeed mPostFeed;
    TextView mAuthor;
    TextView mTime;
    TextView mContent;
    ImageView mPhoto;


    public Recycler_holder(View itemView) {
        super(itemView);

        mAuthor = (TextView) itemView.findViewById(R.id.post_recycler_author);
        mTime = (TextView) itemView.findViewById(R.id.post_recycler_time);
        mContent = (TextView) itemView.findViewById(R.id.post_recycler_content);
        mPhoto = (ImageView) itemView.findViewById(R.id.post_recycler_image);
    }

    public void bind(PostFeed p)
    {
        mPostFeed = p;

        mAuthor.setText(mPostFeed.getEmail());
        mTime.setText(mPostFeed.getTimePosted().toString());
        mContent.setText(mPostFeed.getContent());
        Uri uri = Uri.parse(mPostFeed.getPhotoPath());
        mPhoto.setImageURI(uri);
    }





}
