package org.litesgroup;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import org.jetbrains.annotations.NotNull;

public class LitesApplication extends Application {
    private LitesApplicationComponent mLitesApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mLitesApplicationComponent = DaggerLitesApplicationComponent.builder()
                .androidApplicationModule(new AndroidApplicationModule(this))
                .build();
    }

    public static LitesApplication get(@NotNull Context context) {
        return (LitesApplication) context.getApplicationContext();
    }

    public static LitesApplicationComponent getAppComponent(@NotNull Context context) {
        return get(context).mLitesApplicationComponent;
    }
}
