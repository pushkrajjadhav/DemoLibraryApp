package com.codeplateau.demolibraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.codeplateau.rssfeedlibrary.ChannelProvider;

public class MainActivity extends AppCompatActivity {

    Button btnValidator;
    private static WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView) findViewById(R.id.webview);

        LoadWebView();
    }

    public void LoadWebView(){

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webview.setWebChromeClient(new WebChromeClient());

        ChannelProvider.setUrl("http://vidgyor.com/live/midroll/html/indiatv.html");
        ChannelProvider.setChannelName("asianet");
    }
}
