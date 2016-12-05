package com.cocoapatterns.herocam.herocam.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * Created by joakim on 03/12/2016.
 */

public final class CameraService {

    //
    public static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    //
    public static Camera getCamera() {
        Camera camera = null;
        try {
            camera = Camera.open();
        }
        catch (Exception e){} // Handled implicitly by returning null.
        return camera;
    }
}
