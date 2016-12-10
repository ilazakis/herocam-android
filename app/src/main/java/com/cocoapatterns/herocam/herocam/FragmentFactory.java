package com.cocoapatterns.herocam.herocam;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.Fragment;

import com.cocoapatterns.herocam.herocam.about.AboutFragment;
import com.cocoapatterns.herocam.herocam.camera.CameraFragment;
import com.cocoapatterns.herocam.herocam.help.HelpContainerFragment;

/**
 * Created by joakim on 05/12/2016.
 */

/**
 * Fragment factory, responsible for instantiating the application's fragments.
 * All created fragments are safe to be considered fully inititialised.
 * All non-dynamic fragment dependencies are taken care of throught the factory's
 * dependencies e.g. {@link Resources} for string manipulation.
 *
 * Note: Passing more granular dependencies e.g. the actual app version {@link String} instead of
 * a {@link Resources} reference is encouraged, especially if it makes testing easier.
 */
public final class FragmentFactory {

    //
    private static String WEBSITE_URL = "https://herocamapp.github.io/";

    //
    private Resources resources;

    //
    private Permissions permissions;

    /**
     * FragmentFactory constructor.
     *
     * @param resources
     * @param permissions
     */
    public FragmentFactory(Resources resources, Permissions permissions) {
        this.resources = resources;
        this.permissions = permissions;
    }

    /**
     *
     * @return Returns a new fragment instance.
     */
    public Fragment getAboutFragment() {
        AboutFragment aboutFragment = AboutFragment.newInstance(WEBSITE_URL, getAppVersion());
        return aboutFragment;
    }

    /**
     *
     * @return Returns a new fragment instance.
     */
    public Fragment getHelpFragment() {
        HelpContainerFragment helpFragment = new HelpContainerFragment();
        return helpFragment;
    }

    /**
     *
     * @return Returns a new fragment instance.
     */
    public Fragment getCameraFragment() {
        CameraFragment cameraFragment = CameraFragment.newInstance(permissions);
        return cameraFragment;
    }

    private String getAppVersion() {
        return resources.getString(R.string.about_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE);
    }
}
