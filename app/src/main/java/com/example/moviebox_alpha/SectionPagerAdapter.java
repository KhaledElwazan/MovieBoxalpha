package com.example.moviebox_alpha;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moviebox_alpha.fragments.FavoriteTabFragment;
import com.example.moviebox_alpha.fragments.PopularTabFragment;
import com.example.moviebox_alpha.fragments.TopRatedTabFragment;
import com.example.moviebox_alpha.retrofit.Result;

import java.util.List;


public class SectionPagerAdapter extends FragmentPagerAdapter {


    private final String[] TAB_TITLES = new String[]{"Popular", "Top Rated", "Favorites"};
    private int tabCount;
    private List<Result> movies;


    public SectionPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int i) {


        switch (i) {
            case 0:
                return new PopularTabFragment();
            case 1:
                return new TopRatedTabFragment();
        }


        return new FavoriteTabFragment();
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

}
