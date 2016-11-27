package com.cocoapatterns.herocam.herocam.help;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cocoapatterns.herocam.herocam.R;

public class HelpStepFragment extends Fragment {

    // Views
    private TextView textView;
    private ImageView imageView;

    // Variables
    private int textResId;
    private int imageResId;

    // Constructor
    public static HelpStepFragment newInstance(int textResId, int imageResId) {
        HelpStepFragment fragment = new HelpStepFragment();
        Bundle args = new Bundle();
        args.putInt("textResId", textResId);
        args.putInt("imageResId", imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textResId = getArguments().getInt("textResId");
        imageResId = getArguments().getInt("imageResId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_help_step, container, false);

        textView = (TextView) view.findViewById(R.id.help_step_text);
        textView.setText(textResId);

        imageView = (ImageView) view.findViewById(R.id.help_step_icon);
        imageView.setImageResource(imageResId);

        return view;
    }
}
