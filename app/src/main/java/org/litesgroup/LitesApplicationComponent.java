package org.litesgroup;

import android.app.Application;

import org.litesgroup.app.service.EchoClient;
import org.litesgroup.di.AllApplicationProviders;

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
    AppDatabase getAppDatabase();

    // Should be moved to its own dagger component
    EchoClient getEchoClient();
}
