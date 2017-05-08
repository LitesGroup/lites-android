package org.litesgroup;

import android.app.Application;

import org.litesgroup.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AndroidApplicationModule.class, NetworkModule.class})
public interface LitesApplicationComponent {
    // remove injection methods if downstream modules will perform injection
    public abstract void inject(MainActivity activity);

    Application application();
    OkHttpClient okHttpClient();
}
