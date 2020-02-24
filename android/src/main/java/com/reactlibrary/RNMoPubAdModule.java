package com.reactlibrary;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.mopub.common.MoPub;

public class RNMoPubAdModule extends ReactContextBaseJavaModule {
    
    ReactApplicationContext mReactContext;

    public RNMoPubAdModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNMoPubAd";
    }

    @ReactMethod
    public void initializeAdSDK(String adUnitId, Boolean shouldShowGDPR) {
        AdLibSDK.initializeAdSDK(null, adUnitId, shouldShowGDPR, mReactContext.getCurrentActivity());
    }
}
