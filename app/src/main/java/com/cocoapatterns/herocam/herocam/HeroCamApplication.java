package com.cocoapatterns.herocam.herocam;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by joakim on 10/12/2016.
 */

public final class HeroCamApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {}

    @Override
    public void onActivityStarted(Activity activity) {
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.setFragmentFactory(new FragmentFactory(getResources(), new Permissions()));
    }

    @Override
    public void onActivityResumed(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}
}
