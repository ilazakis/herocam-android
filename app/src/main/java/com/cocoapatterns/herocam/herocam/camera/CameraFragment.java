package com.cocoapatterns.herocam.herocam.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cocoapatterns.herocam.herocam.R;

public class CameraFragment extends Fragment {

    // Variables
    private CameraPreview cameraPreview;

    public CameraFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // If we have permission to use the CAMERA, open the camera.
        if (hasCameraPermission()) {
            openCamera(view);
        }
        // Otherwise ask for permission.
        else {
            askForCameraPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera(getView());
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
        FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_preview);
        preview.addView(cameraPreview);
    }

    private boolean hasCameraPermission() {
        int cameraPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        return cameraPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void askForCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
    }

    private Camera getCamera() {
        return CameraService.getCamera();
    }
}
