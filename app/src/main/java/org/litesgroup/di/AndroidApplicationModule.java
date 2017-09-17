package org.litesgroup.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidApplicationModule {
    @NotNull private final Application mApplication;

    public AndroidApplicationModule(@NotNull Application application) {
        mApplication = application;
    }

    @Provides
    @NotNull
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @NotNull
    @ApplicationContext
    static Context provideAppContext(@NotNull Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @NotNull
    static Resources provideResources(@NotNull @ApplicationContext Context context) {
        return context.getResources();
    }

}
