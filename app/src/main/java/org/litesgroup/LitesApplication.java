package org.litesgroup;

import android.content.Context;

import com.facebook.stetho.Stetho;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.di.AndroidApplicationModule;
import org.litesgroup.network.LitesApi;
import org.litesgroup.network.NetworkModule;

public class LitesApplication extends BaseApplication {
    private LitesApplicationComponent mLitesApplicationComponent;

    @Override
    public void onCreate() {
        Stetho.initializeWithDefaults(this);
        mLitesApplicationComponent = onCreateApplicationComponent();
        super.onCreate();
    }

    @NotNull
    @Override
    protected LitesApplicationComponent onCreateApplicationComponent() {
        return DaggerLitesApplicationComponent.builder()
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

    @Override
    protected void inject() {
        mLitesApplicationComponent.inject(this);
    }
}
