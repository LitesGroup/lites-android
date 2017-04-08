package org.litesgroup;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class LitesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
