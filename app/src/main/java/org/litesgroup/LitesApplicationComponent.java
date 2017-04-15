package org.litesgroup;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidApplicationModule.class})
public interface LitesApplicationComponent {
    public abstract void inject(LitesApplication application);
    public abstract void inject(MainActivity activity);

    Application application();
}
