package com.softomotion.catalogs;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

        //integer to count number of tabs
        int tabCount;
        private String[] tabTitles = new String[]{"Брошури", "Любими"};

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            Log.d("TEST", String.valueOf(position));
            //Returning the current tabs
            switch (position) {
                case 0:
                    BrochuresFragment tab1 = new BrochuresFragment();
                    return tab1;
                case 1:
                    FavouritesFragment tab2 = new FavouritesFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }
}
