package com.cocoapatterns.herocam.herocam.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by joakim on 26/11/2016.
 */

public final class Contact {

    private static String CONTACT_URL = "https://herocamapp.github.io/#support";

    public static void visitWebsiteContact(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(CONTACT_URL)));
    }
}
