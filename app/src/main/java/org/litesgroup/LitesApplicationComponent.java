package org.litesgroup;

import android.app.Application;

import org.litesgroup.LitesApplication;
import org.litesgroup.MainActivity;
import org.litesgroup.app.service.EchoClient;
import org.litesgroup.di.AllApplicationProviders;
import org.litesgroup.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AllApplicationProviders.class})
public interface LitesApplicationComponent {
    void inject(LitesApplication application);

    Application application();
    OkHttpClient okHttpClient();
    Retrofit getRetrofit();

    // Should be moved to its own dagger component
    EchoClient getEchoClient();
}
