package com.test.sharang.myapplication2;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sharang on 5/11/15.
 */
public class OurViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
    }
}
