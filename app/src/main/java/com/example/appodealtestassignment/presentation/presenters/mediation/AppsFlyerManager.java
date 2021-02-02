package com.example.appodealtestassignment.presentation.presenters.mediation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;
/**
 * AppsflyerLink: https://hq1.appsflyer.com/apps/settings?appId=com.zengardenapps.testapp
 * */
public class AppsFlyerManager {
    private static final String TAG = AppsFlyerManager.class.getSimpleName();
    private final String AF_DEV_KEY = "ewVfXy4eavTcRaRzrsKWAA";
    private Context context;
    private IMediationManagerProvider mediationManager;
    private Map<String, Object> eventValue;
    private AppsFlyerConversionListener conversionListener;
    AppsFlyerManager(Activity mediationManager) {
        this.context = mediationManager;
        this.mediationManager = (IMediationManagerProvider) mediationManager;
        initListeners();
        configureAppsFlyer();
        initAppsFlyer();
        initEventData();
    }

    private void initEventData() {
        eventValue = new HashMap<String, Object>();
        eventValue.put("test", "test");
    }

    private void initAppsFlyer() {
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, context);
        AppsFlyerLib.getInstance().start(context);
    }

    private void configureAppsFlyer() {
        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().setLogLevel(AFLogger.LogLevel.VERBOSE);
    }

    private void initListeners() {
         conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {

                for (String attrName : conversionData.keySet()) {
                    Log.d(TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d(TAG, "error getting conversion data: " + errorMessage);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d(TAG, "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(TAG, "error onAttributionFailure : " + errorMessage);
            }
        };
    }

    public void sendCPIEvent() {
        AppsFlyerLib.getInstance().logEvent(context, "dc_cpi_event", eventValue);
        mediationManager.eventCPIWasSend();
    }

    public void sendCPAEvent() {
        AppsFlyerLib.getInstance().logEvent(context, "dc_cpa_event", eventValue);
        mediationManager.eventCPAWasSend();
    }
}
