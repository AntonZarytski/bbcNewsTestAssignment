package com.example.appodealtestassignment.presentation.presenters.mediation;

public interface IMediationManagerProvider {
    void interstitialWasShown();

    void eventCPIWasSend();

    void eventCPAWasSend();
}
