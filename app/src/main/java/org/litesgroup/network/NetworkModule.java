package org.litesgroup.network;

import android.app.Application;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.app.service.EchoClient;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class NetworkModule {
    @NotNull private final String mBaseUrl;

    public NetworkModule(@NotNull String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @NotNull
    OkHttpClient provideOkHttpClient(@NotNull Cache cache,
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

    @Provides
    @NotNull
    Retrofit provideRetrofit(@NotNull OkHttpClient okHttpClient, @NotNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @NotNull
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    // TODO: Should be removed and made its own module
    @Provides
    @NotNull
    EchoClient provideEchoClient(@NotNull RetrofitClientFactory factory) {
        return factory.createRetrofitService(EchoClient.class);
    }
}
