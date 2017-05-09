package org.litesgroup.app.service;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.app.model.Movie;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface EchoClient {
    @POST("echo")
    Observable<Movie> getEcho(@Body @NotNull Movie object);

    @POST("echo")
    Call<Movie> getEchoUsingCall(@Body @NotNull Movie movie);
}
