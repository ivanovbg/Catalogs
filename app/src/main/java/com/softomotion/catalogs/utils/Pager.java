package com.softomotion.catalogs.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softomotion.catalogs.main.BrochuresFragment;
import com.softomotion.catalogs.main.FavouritesFragment;


public class Pager extends FragmentStatePagerAdapter {
        //integer to count number of tabs
        int tabCount;
        private String[] tabTitles = new String[]{"Брошури", "Любими"};
        public BrochuresFragment brochuresFragment;
        public FavouritesFragment favouritesFragment;

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
//            mObservers.deleteObservers();
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

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public Fragment getFragment(String fragment_tag){
            if(fragment_tag == "brochures"){
                return brochuresFragment;
            }else {
                return favouritesFragment;
            }
        }

}
