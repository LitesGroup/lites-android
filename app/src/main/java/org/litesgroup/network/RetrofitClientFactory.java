package org.litesgroup.network;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class RetrofitClientFactory {
    @NotNull private final Retrofit mRetrofit;

    @Inject
    RetrofitClientFactory(@NotNull Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    <T> T createRetrofitService(final Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
