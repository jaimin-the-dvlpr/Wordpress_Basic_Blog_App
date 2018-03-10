package com.jimmy.thetechsamacharjaimin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jimmy.thetechsamacharjaimin.Content_provider;
import com.jimmy.thetechsamacharjaimin.Favourite_DataBase.DBManager;
import com.jimmy.thetechsamacharjaimin.Fragment.HomeFragment;
import com.jimmy.thetechsamacharjaimin.Model.PostItems;
import com.jimmy.thetechsamacharjaimin.R;
import com.squareup.picasso.Picasso;


import java.util.List;




public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostItems> postItems;
    private Context context;


    public PostAdapter(List<PostItems> postItems, Context context) {
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

        holder.txtTitle.setText(postItem.getTitle());
        holder.date.setText(postItem.getDate());

            Picasso.with(context)
                    .load(postItem.getUrl())
                    .into(holder.imgView);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Content_provider.class);
                intent.putExtra("title", postItem.getTitle());
                intent.putExtra("content", postItem.getContent());
                intent.putExtra("image", postItem.getUrl());
                intent.putExtra("date", postItem.getDate());
                intent.putExtra("author", postItem.getAuther());
                view.getContext().startActivity(intent);
            }
        });
        final DBManager dbO;
        final String name=postItem.getTitle();
        final String[] isChecked = {""};
        dbO=new DBManager(context);
        dbO.open();

        Cursor cursor = dbO.fetch(name);
        if ((cursor != null) && (cursor.getCount() > 0)){
            isChecked[0] = cursor.getString(0);
            Toast.makeText(context, isChecked[0], Toast.LENGTH_SHORT).show();

            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_black));

        }
//        if (isChecked[0] == "true"){
//            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_black));
//        }else {
//            holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp));
//        }
        dbO.close();

       holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked[0] == "true") {
                    dbO.open();
                    dbO.delete(name);
                    Toast.makeText(view.getContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                    dbO.close();
                    holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_favorite_border_black_24dp));
                    isChecked[0] = "false";

                } else {
                    dbO.open();
                    dbO.insert(postItem.getTitle(), postItem.getUrl(),postItem.getDate());
                    dbO.close();
                    Toast.makeText(view.getContext(), "Data inserted", Toast.LENGTH_SHORT).show();

                    holder.fav.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_favorite_black));
                    isChecked[0] = "true";
                }
            }
        });

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
