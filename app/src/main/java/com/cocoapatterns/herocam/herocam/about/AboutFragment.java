package com.cocoapatterns.herocam.herocam.about;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.cocoapatterns.herocam.herocam.R;

public class AboutFragment extends Fragment {

    private static String WEBSITE_URL_BUNDLE_KEY = "websiteURL";
    private static String APP_VERSION_BUNDLE_KEY = "appVersion";

    // Variables
    private String websiteUrl;
    private String appVersion;

    // Constructor
    public static AboutFragment newInstance(String websiteURL, String appVersion) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(WEBSITE_URL_BUNDLE_KEY, websiteURL);
        args.putString(APP_VERSION_BUNDLE_KEY, appVersion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        websiteUrl = getArguments().getString(WEBSITE_URL_BUNDLE_KEY);
        appVersion = getArguments().getString(APP_VERSION_BUNDLE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView versionTextView = (TextView) view.findViewById(R.id.about_version);
        versionTextView.setText(getVersion());

        ImageButton learnMoreButton = (ImageButton) view.findViewById(R.id.about_learn_more_icon);
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                    getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl)));
                                               }
                                           }
        );

        return view;
    }

    private String getVersion() {
        return appVersion;
    }
}
