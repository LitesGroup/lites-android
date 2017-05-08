package org.litesgroup;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

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

public class MainActivity extends BaseActivity {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");
    public static final String SAMPLE_JSON = "{\n" +
            "\"name\":\"John\",\n" +
            "\"age\":30,\n" +
            "\"cars\":[ \"Ford\", \"BMW\", \"Fiat\" ]\n" +
            "}";

    private EditText mEditText;

    @Inject Application mApplication;
    @Inject OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(SAMPLE_JSON);

        // Testing DI
        runTestDI();
        // Testing network
        Button networkButton = (Button) findViewById(R.id.network_button);
        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runDummyNetworkRequest();
            }
        });
    }

    @Override
    public void inject(LitesApplicationComponent component) {
        component.inject(this);
    }

    private void runTestDI() {
        Toast.makeText(this, mApplication.getClass().toString() , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "testing dependency injection", Toast.LENGTH_LONG).show();
    }

    private void runDummyNetworkRequest() {
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
