package org.litesgroup;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    @NotNull
    protected abstract LitesApplicationComponent onCreateApplicationComponent();

    protected abstract void inject();
}
