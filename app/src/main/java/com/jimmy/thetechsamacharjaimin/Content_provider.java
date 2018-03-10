package com.jimmy.thetechsamacharjaimin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimmy.thetechsamacharjaimin.Model.PostItems;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jaimin_Patel on 3/7/2018.
 */

public class Content_provider  extends AppCompatActivity{
    private List<PostItems> postItems;
    private static final String URL_DATA = "http://www.thetechsamachar.com/api/get_posts/?count=1/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_catagory);

        ImageView imgView = findViewById(R.id.postThumbnail);

        TextView title = findViewById(R.id.postTitle);
//        TextView date = findViewById(R.id.date);
        TextView author = findViewById(R.id.authorName);
        TextView content = findViewById(R.id.contentBlock);

        String postTitle = getIntent().getStringExtra("title");
        String postImg = getIntent().getStringExtra("image");
        String postDate = getIntent().getStringExtra("date");
        String postAuthor = getIntent().getStringExtra("author");
        String postContent = getIntent().getStringExtra("content");

        title.setText(postTitle);
//        date.setText(postDate);
        author.setText(String.format("%s%s", postAuthor, postDate));
        content.setText(postContent);
        Picasso.with(this)
                .load(postImg)
                .into(imgView);

    }
}
