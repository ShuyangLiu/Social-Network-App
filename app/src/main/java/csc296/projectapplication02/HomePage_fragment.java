package csc296.projectapplication02;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import csc296.projectapplication02.model.PostFeed;
import csc296.projectapplication02.model.UserCollection;


public class HomePage_fragment extends Fragment
{

    public static final String EMAIL_KEY =
            "csc296.projectapplication02.LoginPage.EMAIL";

    UserCollection mCollection;
    Recycler_adapter mAdapter;
    RecyclerView mRecyclerView;

    String mEmail;


    public HomePage_fragment()
    {
        /* Required empty public constructor */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        /*get the email argument*/
        mEmail = getArguments().getString(EMAIL_KEY);

        /*inflate the view to the fragment*/
        View view = inflater.inflate(R.layout.fragment_home_page_fragment,
                container, false);

        /*set an recycler view adapter to the fragment*/
        mCollection = UserCollection.get(getActivity());
        mRecyclerView = (RecyclerView)view.findViewById(R.id.homepage_recycler_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateUI()
    {
        List<PostFeed> list = mCollection.getPostFeed(mEmail);

        if(mAdapter==null)
        {
            mAdapter = new Recycler_adapter(list);
            mRecyclerView.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.setPosts(list);
        }
    }




}
