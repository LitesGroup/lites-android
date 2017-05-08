package org.litesgroup.network;

import android.app.Application;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    @Provides
    @NotNull
    OkHttpClient providOkHttpClient(@NotNull Cache cache,
                                    @NotNull HttpLoggingInterceptor httpLoggingInterceptor,
                                    @NotNull StethoInterceptor stethoInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .build();
    }

    @Provides
    @NotNull
    Cache provideCache(@NotNull Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @NotNull
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NotNull
    StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }
}
