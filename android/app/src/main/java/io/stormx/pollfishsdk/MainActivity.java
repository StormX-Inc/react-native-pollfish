package io.stormx.pollfishsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.main.PollFish;
import com.pollfish.main.PollFish.ParamsBuilder;

public class MainActivity extends AppCompatActivity implements PollfishOpenedListener, PollfishClosedListener {

    public static final String appKey = "appkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        PollFish.initWith(this, new ParamsBuilder(getIntent().getStringExtra(appKey)).build());
    }

    @Override
    public void onPollfishOpened() {

    }

    @Override
    public void onPollfishClosed() {
        this.finish();
    }
}
