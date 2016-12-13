package com.cocoapatterns.herocam.herocam;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by joakim on 11/12/2016.
 */

public final class HeroCamTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockApplication.class.getName(), context);
    }
}
