package com.cocoapatterns.herocam.herocam.about;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cocoapatterns.herocam.herocam.BuildConfig;
import com.cocoapatterns.herocam.herocam.R;

public class AboutFragment extends Fragment {

    private static String WEBSITE_URL = "https://herocamapp.github.io/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView versionTextView = (TextView) view.findViewById(R.id.about_version);
        versionTextView.setText(getVersion());

        ImageButton learnMoreButton = (ImageButton) view.findViewById(R.id.about_learn_more_icon);
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE_URL)));
                                               }
                                           }
        );

        return view;
    }

    private String getVersion() {
        return getResources().getString(R.string.about_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE);
    }
}
