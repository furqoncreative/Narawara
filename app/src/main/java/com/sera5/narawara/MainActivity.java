package com.sera5.narawara;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

import com.sera5.narawara.Fragments.AccountFragment;
import com.sera5.narawara.Fragments.FeedFragment;
import com.sera5.narawara.Fragments.GamesFragment;
import com.sera5.narawara.Fragments.MainPageFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadFragment(new MainPageFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);

        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MyFragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new MainPageFragment();
                break;
            case R.id.feed_menu:
                fragment = new FeedFragment();
                break;
            case R.id.games_menu:
                fragment = new GamesFragment();
                break;
            case R.id.account_menu:
                fragment = new AccountFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
