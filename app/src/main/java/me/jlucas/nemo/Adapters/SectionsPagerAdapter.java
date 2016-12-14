package me.jlucas.nemo.Adapters;

/**
 * Created by hansolo on 13/12/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.jlucas.nemo.Fragments.GalleryFragment;
import me.jlucas.nemo.Fragments.MainFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MainFragment.newInstance();
            case 1:
                return GalleryFragment.newInstance();
        }
        return MainFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Galeria";
        }
        return null;
    }
}
