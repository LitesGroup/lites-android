package org.litesgroup.playground;

import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.BaseActivity;
import org.litesgroup.R;
import org.litesgroup.playground.rxutils.Logger;

public class RxJavaPlaygroundActivity extends BaseActivity implements Logger {
    private TextView textView;
    private String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_playground);
        textView = (TextView) findViewById(R.id.text);
        RxJavaPlayground rxJavaPlayground = new RxJavaPlayground(this);
        rxJavaPlayground.run();
    }

    @Override
    public void log(@NotNull String s) {
        log += Thread.currentThread().getName() + ": " + s + "\n";
        textView.setText(log);
    }
}
