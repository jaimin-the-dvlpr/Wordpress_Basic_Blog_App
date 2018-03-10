package com.jimmy.thetechsamacharjaimin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.jimmy.thetechsamacharjaimin.Fragment.CatagoryFragment;
import com.jimmy.thetechsamacharjaimin.Fragment.FavouriteFragment;
import com.jimmy.thetechsamacharjaimin.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    AHBottomNavigation navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
//        }

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        navigation.setOnTabSelectedListener(this);
        this.CreateNav();
    }

    private void CreateNav() {
        //Items Create
        AHBottomNavigationItem home = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp);
        AHBottomNavigationItem cat = new AHBottomNavigationItem("Category", R.drawable.ic_dashboard_black_24dp);
        AHBottomNavigationItem fav = new AHBottomNavigationItem("Favourite", R.drawable.ic_favorite_black);

        //ADD Profile
        navigation.addItem(home);
        navigation.addItem(cat);
        navigation.addItem(fav);

        //Set Properties
        navigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        navigation.setAccentColor(Color.parseColor("#F63D2B"));
        navigation.setInactiveColor(Color.parseColor("#747474"));

        // Set current item programmatically
        navigation.setCurrentItem(0);

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        Fragment fragment;
        switch (position){
            case 0: {
                fragment = new HomeFragment();
                    loadfragment(fragment);
                    return true;

            }
            case 1: {
                fragment = new CatagoryFragment();
                loadfragment(fragment);
                return true;

            }
            case 2: {
                fragment = new FavouriteFragment();
                loadfragment(fragment);
                return true;

            }
        }

        return true;
    }



    private void loadfragment(Fragment fragment) {
        // load fragments here..

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
