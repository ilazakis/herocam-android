package com.cocoapatterns.herocam.herocam;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

/**
 * Created by joakim on 10/12/2016.
 */

/**
 * User permissions wrapper.
 */
public final class Permissions implements Parcelable {

    /** Default constructor. */
    Permissions() {}

    // region API

    /**
     * Checks whether the user has granted the {@link android.Manifest.permission#CAMERA} permission.
     *
     * @param context
     * @return True if the permission is granted, false otherwise.
     */
    public boolean hasCameraPermission(Context context) {
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        return cameraPermission == PackageManager.PERMISSION_GRANTED;
    }

    // endregion

    // region Parcelable implementation

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {}

    protected Permissions(Parcel in) {}

    public static final Creator<Permissions> CREATOR = new Creator<Permissions>() {
        @Override
        public Permissions createFromParcel(Parcel in) { return new Permissions(in); }

        @Override
        public Permissions[] newArray(int size) { return new Permissions[size]; }
    };

    // endregion
}
