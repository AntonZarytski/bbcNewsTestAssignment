package com.example.appodealtestassignment.presentation.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.appodealtestassignment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends Activity {
    public static final String URI_KEY = "NEWS_URI";
    @BindView(R.id.news_web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ButterKnife.bind(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        loadForm();
    }

    private void loadForm() {
        String uri = getIntent().getStringExtra(URI_KEY);
        webView.loadUrl(uri);
    }

}