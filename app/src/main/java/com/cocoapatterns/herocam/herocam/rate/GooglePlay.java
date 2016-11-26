package com.cocoapatterns.herocam.herocam.rate;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by joakim on 26/11/2016.
 */

public final class GooglePlay {

    public static void visitGooglePlay(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent visitGooglePlay = new Intent(Intent.ACTION_VIEW, uri);
        visitGooglePlay.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(visitGooglePlay);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}
