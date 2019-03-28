package com.chrism.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new BrowseFragment())
                    .commit();
        }

//        HTTPHandler httpHandler = new HTTPHandler(this);
//        httpHandler.execute();

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()){
                case R.id.favs:
                    this.navigateTo(new FavFragment(), false);
                    break;
                case R.id.browse:
                    this.navigateTo(new BrowseFragment(), false);
                case R.id.search:
//                    this.navigateTo(new SearchFragment(), false);
                    break;
            }

            return true;
        });

    }

    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

}
