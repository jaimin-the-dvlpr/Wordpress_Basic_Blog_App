package com.jimmy.thetechsamacharjaimin.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jimmy.thetechsamacharjaimin.Favourite_DataBase.DBManager;
import com.jimmy.thetechsamacharjaimin.Favourite_DataBase.DatabaseHelper;
import com.jimmy.thetechsamacharjaimin.Model.FavPost;
import com.jimmy.thetechsamacharjaimin.R;


/**
 * Created by Jaimin_Patel on 2/22/2018.
 */

public class FavouriteFragment extends android.support.v4.app.Fragment {


    private DBManager dbManager;

    final String[] from = new String[] {
            DatabaseHelper.Name, DatabaseHelper.Image,DatabaseHelper.date};

    ListView listview;
    private SimpleCursorAdapter adapter;
    final int[] to = new int[] { R.id.txtTitle, R.id.imgView ,R.id.date};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.favourite_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Favourite");




        dbManager = new DBManager(getActivity());
        dbManager.open();

        Cursor cursor = dbManager.getAllCode();

        listview =  getView().findViewById(R.id.favlistfrag);
        listview.setEmptyView(getView().findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.customlistview, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);

    }
}