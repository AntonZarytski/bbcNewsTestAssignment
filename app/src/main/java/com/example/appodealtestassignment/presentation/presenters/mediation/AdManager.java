package com.example.appodealtestassignment.presentation.presenters.mediation;

import android.app.Activity;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appsflyer.AppsFlyerLib;
import com.example.appodealtestassignment.BuildConfig;
import com.example.appodealtestassignment.R;

/**
 * AppLink: https://app.appodeal.com/admin/apps/692563
 * */
public class AdManager {
    private Activity context;
    AppsFlyerManager appsFlyerManager;
    private IMediationManagerProvider mediationManager;
    static final String APP_KEY = "8b5ad779a26b3591b32fc3e44dbee4fe5ff30d6babc810b2";
    private final int adType = Appodeal.INTERSTITIAL | Appodeal.BANNER;
    private boolean isTesting = true;
    private InterstitialCallbacks callbacks;

    public AdManager(Activity context){
        this.context = context;
        mediationManager = (IMediationManagerProvider) context;
        initListeners();
        initAppodeal();
        appsFlyerManager = new AppsFlyerManager(context);
    }

    private void initListeners() {
        callbacks = new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {

            }

            @Override
            public void onInterstitialFailedToLoad() {

            }

            @Override
            public void onInterstitialShown() {
                appsFlyerManager.sendCPIEvent();
            }

            @Override
            public void onInterstitialShowFailed() {

            }

            @Override
            public void onInterstitialClicked() {
                appsFlyerManager.sendCPAEvent();
            }

            @Override
            public void onInterstitialClosed() {
                interstitialWasShown();
            }

            @Override
            public void onInterstitialExpired() {

            }
        };
        Appodeal.setInterstitialCallbacks(callbacks);
    }

    private void initAppodeal() {
        Appodeal.setLogLevel(com.appodeal.ads.utils.Log.LogLevel.verbose);
        String AppsFlyerUID =  AppsFlyerLib.getInstance().getAppsFlyerUID(context);
        Appodeal.setExtraData("attribution_id", AppsFlyerUID);
        if (BuildConfig.DEBUG){
            isTesting = true;
        }
        Appodeal.setTesting(isTesting);
        Appodeal.initialize(context, APP_KEY, adType, true);
        setBannerViewId();
    }
    private void setBannerViewId(){
        Appodeal.setBannerViewId(R.id.bannerViewId);
    }

    public void showBanner(){
        Appodeal.show(context, Appodeal.BANNER_VIEW);
    }
    public void hideBanner() {
        Appodeal.hide(context, Appodeal.BANNER_VIEW);
    }
    public void showInterstitial(){
        if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
            Appodeal.show(context, Appodeal.INTERSTITIAL);
        }
    }

    private void interstitialWasShown(){
        mediationManager.interstitialWasShown();
    }
}
