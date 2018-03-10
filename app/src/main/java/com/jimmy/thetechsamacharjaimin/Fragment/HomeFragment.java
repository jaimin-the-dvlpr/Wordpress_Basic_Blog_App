package com.jimmy.thetechsamacharjaimin.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jimmy.thetechsamacharjaimin.Adapter.PostAdapter;
import com.jimmy.thetechsamacharjaimin.Content_provider;
import com.jimmy.thetechsamacharjaimin.Model.PostItems;
import com.jimmy.thetechsamacharjaimin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaimin_Patel on 3/7/2018.
 */

public class HomeFragment extends Fragment  {
    View.OnClickListener mOnClickListener;
   private List<PostItems> postItems;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar spinner;
    private SwipeRefreshLayout swipe;
    private static final String URL_DATA = "Enter URL Here....";
    String title, date, content, image, author;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView)  getView().findViewById(R.id.recyclerView);
        spinner = (ProgressBar) getView().findViewById(R.id.progressbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postItems = new ArrayList<>();
             loadTechdata();

        swipe =  getView().findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
                        loadTechdata();
                        swipe.setRefreshing(false);
            }
        });
    }

    private void loadTechdata() {
        spinner.setVisibility(View.VISIBLE);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s){

                        try {
                            spinner.setVisibility(View.GONE);

                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("posts");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                JSONObject writer = ob.getJSONObject("author");
//                                JSONObject img = ob.getJSONObject("attachments");
//                                JSONObject imgmed = img.getJSONObject("0");


                                 title = Html.fromHtml((String) ob.get("title")).toString();
                                 date = Html.fromHtml((String) ob.get("date")).toString();
                                 author = Html.fromHtml((String) writer.get("name")).toString();
                                 image = String.valueOf(Html.fromHtml(ob.getString("thumbnail")));
                                 content = String.valueOf(Html.fromHtml(ob.getString("content")));


                                PostItems postItem = new PostItems(
                                        title,
                                        author,
                                        date,
                                        image,
                                        content


                                );

                                postItems.add(postItem);
                            }

                            adapter = new PostAdapter(postItems, getActivity());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        spinner.setVisibility(View.GONE);

                        Snackbar bar = Snackbar.make(getView(), "No Internet Connection",30000)
                                .setAction("Retry", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    loadTechdata();
                                    }
                                });

                        bar.show();


                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}