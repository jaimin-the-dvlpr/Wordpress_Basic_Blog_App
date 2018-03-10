package com.jimmy.thetechsamacharjaimin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jimmy.thetechsamacharjaimin.Content_provider;
import com.jimmy.thetechsamacharjaimin.Favourite_DataBase.DBManager;
import com.jimmy.thetechsamacharjaimin.Model.PostItems;
import com.jimmy.thetechsamacharjaimin.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private List<PostItems> postItems;
    private Context context;


    public FavAdapter(List<PostItems> postItems, Context context) {
        this.postItems = postItems;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customlistview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PostItems postItem = postItems.get(position);

//        holder.txtTitle.setText(postItem.getTitle());
//        holder.date.setText(postItem.getDate());
//
//        Picasso.with(context)
//                .load(postItem.getUrl())
//                .into(holder.imgView);

        holder.txtTitle.setText( Html.fromHtml(postItem.getTitle()));

    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }


    public class ViewHolder  extends RecyclerView.ViewHolder {


        public TextView txtTitle;
        public TextView date;
        public ImageView imgView;
        public Button fav;
        public CardView card;

        public ViewHolder(final View itemView) {
        super(itemView);

            txtTitle =  itemView.findViewById(R.id.txtTitle);
            date =  itemView.findViewById(R.id.date);
            imgView = itemView.findViewById(R.id.imgView);
            card  =  itemView.findViewById(R.id.cardlayout);
            fav  =  itemView.findViewById(R.id.btnfav);
    }

}
}
