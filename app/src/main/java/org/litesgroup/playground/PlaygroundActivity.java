package org.litesgroup.playground;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.BaseActivity;
import org.litesgroup.LitesApplication;
import org.litesgroup.R;
import org.litesgroup.app.model.Movie;
import org.litesgroup.app.service.EchoClient;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

public class PlaygroundActivity extends BaseActivity{

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");
    public static final String SAMPLE_JSON = "{\n" +
            "\"name\":\"John\",\n" +
            "\"age\":30,\n" +
            "\"cars\":[ \"Ford\", \"BMW\", \"Fiat\" ]\n" +
            "}";

    private EditText mEditText;

    @Inject
    Application mApplication;
    @Inject
    OkHttpClient mOkHttpClient;
    @Inject
    EchoClient mEchoClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playground_activity);
        setTitle(R.string.playground_activity);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(SAMPLE_JSON);

        // Testing DI
        runTestDI();
        // Testing network
        Button networkButton = (Button) findViewById(R.id.network_button);
        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Remove these methods and make samples
//                runDummyNetworkRequestUsingOkhttp();
                runDummyNetworkRequestUsingRetrofit();
//                runDummyNetworkRequestUsingRetrofitCall();
            }
        });
    }

    @NotNull
    @Override
    protected PlaygroundComponent onCreateComponent() {
        return DaggerPlaygroundComponent.builder()
                .litesApplicationComponent(LitesApplication.getAppComponent(this))
                .build();
    }

    @Override
    protected void onComponentCreated() {
        super.onComponentCreated();
        PlaygroundComponent component = getComponent();
        component.inject(this);
    }

    private void runTestDI() {
        Toast.makeText(this, mApplication.getClass().toString() , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "testing dependency injection", Toast.LENGTH_LONG).show();
    }

    private void runDummyNetworkRequestUsingRetrofit() {
        final Movie movie = new Movie("12", "Armageddon", 1991);
        Observable<Movie> movieObservable = mEchoClient.getEcho(movie);
        movieObservable
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        TextView textView = (TextView) findViewById(R.id.text);
                        textView.setText(movie.getId() + " " + movie.getTitle() + " " + movie.getYear());
                    }
                });
    }

    private void runDummyNetworkRequestUsingRetrofitCall() {
        final Movie movie = new Movie("12", "Armageddon", 1991);
        retrofit2.Call<Movie> call = mEchoClient.getEchoUsingCall(movie);
        call.enqueue(new retrofit2.Callback<Movie>() {
            @Override
            public void onResponse(retrofit2.Call<Movie> call, retrofit2.Response<Movie> response) {
                if(response.isSuccessful()) {
                    final Movie responseMovie = response.body();
                    TextView textView = (TextView) findViewById(R.id.text);
                    textView.setText(responseMovie.getId() + " " + responseMovie.getTitle() + " " + responseMovie.getYear());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Movie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void runDummyNetworkRequestUsingOkhttp() {
        @NotNull Request request = new Request.Builder()
                .url("http://dev.litesgroup.org/echo")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, mEditText.getText().toString()))
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView) findViewById(R.id.text);
                            try {
                                textView.setText(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
