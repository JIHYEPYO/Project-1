package com.example.ncs.project1;

/**
 * Created by NCS on 2017-05-16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ShowFavoritesFragment.getInstance();
            case 1:
                return RegisterFavoritesFragment.getInstance();
            case 2:
                return RemoveFavoritesFragment.getInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "관심사이트 보기";
            case 1:
                return "관심사이트 등록";
            case 2:
                return "관심사이트 삭제";
        }
        return "";
    }
}