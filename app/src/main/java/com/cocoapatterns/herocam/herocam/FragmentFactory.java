package com.cocoapatterns.herocam.herocam;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.Fragment;

import com.cocoapatterns.herocam.herocam.about.AboutFragment;

/**
 * Created by joakim on 05/12/2016.
 */

public final class FragmentFactory {

    //
    private static String WEBSITE_URL = "https://herocamapp.github.io/";

    //
    private Resources resources;

    public FragmentFactory(Resources resources) {
        this.resources = resources;
    }

    public Fragment getAboutFragment() {
        AboutFragment aboutFragment = AboutFragment.newInstance(WEBSITE_URL, getAppVersion());
        return aboutFragment;
    }

    private String getAppVersion() {
        return resources.getString(R.string.about_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE);
    }
}
