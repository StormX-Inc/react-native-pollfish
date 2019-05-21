package io.stormx.pollfishsdk;

import android.content.Intent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.pollfish.main.PollFish;
import com.pollfish.main.PollFish.ParamsBuilder;
import com.pollfish.interfaces.PollfishClosedListener;

public class RNPollfishModule extends ReactContextBaseJavaModule {

    public RNPollfishModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNPollfish";
    }

    @ReactMethod
    public void startOfferwall(final String appKey, final String userId) {
        sendEvent("onPollfishStarted");

        getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PollFish.initWith(getCurrentActivity(), new ParamsBuilder(appKey)
                    .pollfishClosedListener(new PollfishClosedListener() {
                        @Override
                        public void onPollfishClosed() {
                            PollFish.hide();
                            sendEvent("onPollfishClosed");
                        }
                    })
                    .requestUUID(userId)
                    .build());
            }
        });
    }

    private void sendEvent(String eventValue) {
        WritableMap event = Arguments.createMap();
        event.putString("value", eventValue);

        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onPollfishEvent", event);
    }

}
