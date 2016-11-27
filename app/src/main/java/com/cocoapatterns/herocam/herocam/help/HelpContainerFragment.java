package com.cocoapatterns.herocam.herocam.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cocoapatterns.herocam.herocam.R;

/**
 * Created by joakim on 26/11/2016.
 */

public final class HelpContainerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_container, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.help_viewPager);
        HelpPagerAdapter pagerAdapter = new HelpPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        return view;
    }
}
