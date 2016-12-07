package com.cocoapatterns.herocam.herocam.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.Nullable;

/**
 * Created by joakim on 03/12/2016.
 */

/**
 * Wrapper class around camera capabilities.
 *
 * Use it to query camera availability and to get access to {@link Camera} instances.
 *
 * Note: Consider avoiding static methods entirely for this if testability is affected.
 */
public final class CameraService {

    /**
     * Query this method before trying to get a {@link Camera} instance.
     * Alternatively, check if the result of {@link #getCamera()} is null.
     *
     * TODO: Consider passing the package manager directly instead of the context.
     *
     * @param context The current context.
     * @return True if a camera is available or false if not.
     */
    public static boolean isCameraAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * @return A {@link Camera} instance or null if one could not be retrieved.
     */
    @Nullable
    public static Camera getCamera() {
        Camera camera = null;
        try {
            camera = Camera.open();
        }
        catch (Exception e){} // Handled implicitly by returning null.
        return camera;
    }
}
