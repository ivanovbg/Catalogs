package com.softomotion.catalogs.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softomotion.catalogs.main.BrochuresFragment;
import com.softomotion.catalogs.main.FavouritesFragment;


public class Pager extends FragmentStatePagerAdapter {
    int tabCount;
    private String[] tabTitles;
    public BrochuresFragment brochuresFragment;
    public FavouritesFragment favouritesFragment;


    public Pager(FragmentManager fm, int tabCount, String[] tabTitles) {
        super(fm);
        this.tabCount = tabCount;
        this.tabTitles = tabTitles;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                brochuresFragment = new BrochuresFragment();
                return brochuresFragment;
            case 1:
                favouritesFragment = new FavouritesFragment();
                return favouritesFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
