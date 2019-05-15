package io.stormx.pollfishsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.main.PollFish;
import com.pollfish.main.PollFish.ParamsBuilder;

public class MainActivity extends AppCompatActivity implements PollfishOpenedListener, PollfishClosedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        PollFish.initWith(this, new ParamsBuilder("2ad6e857-2995-4668-ab95-39e068faa558").build());
    }

    @Override
    public void onPollfishOpened() {

    }

    @Override
    public void onPollfishClosed() {
        this.finish();
    }
}
