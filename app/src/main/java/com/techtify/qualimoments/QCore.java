package com.techtify.qualimoments;

import android.content.Context;
import android.content.Intent;

import com.facebook.login.LoginManager;
import com.techtify.qualimoments.activity.QSplashActivity;

public class QCore {
    public static void logout(Context context) {
        LoginManager.getInstance().logOut();
        context.startActivity(new Intent(context, QSplashActivity.class));
    }
}
