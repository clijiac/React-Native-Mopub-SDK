package com.reactlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.common.privacy.ConsentDialogListener;
import com.mopub.mobileads.MoPubErrorCode;

import com.mopub.mobileads.FacebookAdapterConfiguration;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * Created by usamaazam on 29/03/2019.
 */

public class AdLibSDK {

    static void initializeAdSDK(final RNMoPubBanner banner, final String adUnitId, final Boolean shouldShowGDPR, final Activity context) {

        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                /* Map<String, String> facebookConfig = new HashMap<>();
                facebookConfig.put("banner", "");
                facebookConfig.put("interstitial", "");

                MoPubLog.d("----------------FACEBOOK: " + FacebookAdapterConfiguration.class.getName()); */

                SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adUnitId)
                        .withLogLevel(MoPubLog.LogLevel.DEBUG)
                        /* .withMediatedNetworkConfiguration(FacebookAdapterConfiguration.class.getName(), facebookConfig)*/
                        .withLegitimateInterestAllowed(false)
                        .build();

                MoPub.initializeSdk(context, sdkConfiguration, initSdkListener());

            }

            private SdkInitializationListener initSdkListener() {
                return new SdkInitializationListener() {
                    @Override
                    public void onInitializationFinished() {
                        if (banner != null) {
                            banner.setAdUnitId(adUnitId);
                            banner.loadAd();
                        }

                        final PersonalInfoManager mPersonalInfoManager = MoPub.getPersonalInformationManager();
                        
                        if (shouldShowGDPR && mPersonalInfoManager.shouldShowConsentDialog()) {
                            mPersonalInfoManager.loadConsentDialog(new ConsentDialogListener() {

                                @Override
                                public void onConsentDialogLoaded() {
                                    if (mPersonalInfoManager != null) {
                                        mPersonalInfoManager.showConsentDialog();
                                    }
                                }

                                @Override
                                public void onConsentDialogLoadFailed(@NonNull MoPubErrorCode moPubErrorCode) {
                                    MoPubLog.i("Consent dialog failed to load.");
                                }
                            });
                        }
                    }
                };
            }
        };
        mainHandler.post(myRunnable);

    }
}
