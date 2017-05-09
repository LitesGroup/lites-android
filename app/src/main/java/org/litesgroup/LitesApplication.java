package org.litesgroup;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.network.LitesApi;
import org.litesgroup.network.NetworkModule;

public class LitesApplication extends Application {
    private LitesApplicationComponent mLitesApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mLitesApplicationComponent = DaggerLitesApplicationComponent.builder()
                .androidApplicationModule(new AndroidApplicationModule(this))
                .networkModule(new NetworkModule(LitesApi.BASE_URL))
                .build();
    }

    public static LitesApplication get(@NotNull Context context) {
        return (LitesApplication) context.getApplicationContext();
    }

    public static LitesApplicationComponent getAppComponent(@NotNull Context context) {
        return get(context).mLitesApplicationComponent;
    }
}
