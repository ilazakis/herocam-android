package com.cocoapatterns.herocam.herocam.help;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cocoapatterns.herocam.herocam.R;

/**
 * Created by joakim on 26/11/2016.
 */

public final class HelpPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_PAGES = 3;

    // Initialization
    public HelpPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HelpStepFragment.newInstance(R.string.help_text_step_1, R.drawable.help_step_one);
            case 1:
                return HelpStepFragment.newInstance(R.string.help_text_step_2, R.drawable.help_step_two);
            case 2:
                return HelpStepFragment.newInstance(R.string.help_text_step_3, R.drawable.help_step_three);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}
