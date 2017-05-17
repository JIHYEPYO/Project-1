package com.example.ncs.project1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by NCS on 2017-05-16.
 */

public class FavoritesActivity extends AppCompatActivity {

    private static ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(pager);

        String str = getIntent().getStringExtra("particularFragment");
        if (str != null) {
            switch (str.toString()) {
                case "showFavorites":
                    setPager(0);
                    break;
                case "registerFavorites":
                    setPager(1);
                    break;
                case "removeFavorites":
                    setPager(2);
                    break;
            }
        }
    }

    public static void setPager(int index) {
        pager.setCurrentItem(index);
    }
}
