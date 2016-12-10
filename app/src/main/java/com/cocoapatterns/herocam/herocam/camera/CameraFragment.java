package com.cocoapatterns.herocam.herocam.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cocoapatterns.herocam.herocam.Permissions;
import com.cocoapatterns.herocam.herocam.R;

public class CameraFragment extends Fragment implements View.OnClickListener {

    /** The Request Code used to identify the "camera permission" request. */
    private final int CAMERA_PERMISSION_REQUEST_CODE = 1;

    /** Permissions "extra" key. */
    private final static String PERMISSION_EXTRA_KEY = "com.cocoapatterns.herocam.camera.CameraFragment.permission";

    /** Permissions "checker". Used to check if we have access to the CAMERA. */
    private Permissions permissions;

    /** The "Camera Preview" view. */
    private CameraPreview cameraPreview;

    /** The "Camera View Holder" layout. */
    private FrameLayout cameraPreviewHolder;

    /** The "take picture" button. */
    private AppCompatImageButton captureButton;

    /**
     * CameraFragment constructor.
     *
     * @param permissions Used to check if we have access to the CAMERA.
     * @return An instance of CameraFragment.
     */
    public static CameraFragment newInstance(Permissions permissions) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putParcelable(PERMISSION_EXTRA_KEY, permissions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = getArguments().getParcelable(PERMISSION_EXTRA_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_camera, container, false);
        cameraPreviewHolder = (FrameLayout) view.findViewById(R.id.camera_preview);
        captureButton = (AppCompatImageButton) view.findViewById(R.id.camera_button);
        captureButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (permissions.hasCameraPermission(getContext())) {
            openCamera(view);
        } else {
            askForCameraPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                if (isPermissionGranted(grantResults)) {
                    openCamera(getView());
                } else {
                    // TODO: Show a "camera is needed for the app to work" message.
                }
        }
    }

    private void openCamera(View view) {
        // Try to get a Camera to work with and proceed if we succeeded.
        Camera camera = getCamera();
        if (camera != null) { updateCameraPreview(camera, view); }
    }

    private void updateCameraPreview(Camera camera, View view) {
        cameraPreview = new CameraPreview(this.getContext(), camera);
        cameraPreviewHolder.addView(cameraPreview);
    }

    private void askForCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }

    private Camera getCamera() {
        return CameraService.getCamera();
    }

    private boolean isPermissionGranted(@NonNull int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View view) {
        // take picture.
    }
}
