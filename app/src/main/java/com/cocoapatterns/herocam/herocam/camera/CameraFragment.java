package com.cocoapatterns.herocam.herocam.camera;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        cameraPreview = new CameraPreview(this.getContext(), CameraService.getCamera());
        FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_preview);
        preview.addView(cameraPreview);

        return view;
    }
}
