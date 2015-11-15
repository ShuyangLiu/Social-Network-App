package csc296.projectapplication02;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import csc296.projectapplication02.model.PostFeed;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_holder>
{

    List<PostFeed> posts;

    public Recycler_adapter(List<PostFeed> posts) {
        this.posts = posts;
    }

    @Override
    public Recycler_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_recycler_layout, parent, false);

        return new Recycler_holder(view);
    }

    @Override
    public void onBindViewHolder(Recycler_holder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<PostFeed> list)
    {
        posts = list;
        notifyDataSetChanged();
    }

}
