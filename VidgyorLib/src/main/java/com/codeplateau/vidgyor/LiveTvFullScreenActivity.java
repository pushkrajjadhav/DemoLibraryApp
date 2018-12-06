package com.codeplateau.vidgyor;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.codeplateau.rssfeedlibrary.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class LiveTvFullScreenActivity extends AppCompatActivity {

    public static WebView webview;
    public static String ChannelName;
    public static AdView adView_top,adView_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv_full_screen);

        LoadWebView();
    }

    public void LoadWebView(){

        ChannelName = getIntent().getStringExtra("ChannelName");

        webview = (WebView) findViewById(R.id.webview);
        adView_top = (AdView) findViewById(R.id.adView_top);
        adView_bottom = (AdView) findViewById(R.id.adView_bottom);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webview.setWebChromeClient(new WebChromeClient());

        webview.setWebViewClient(new WvClient());
        Vidgyor.setWebView(ChannelName, webview);


       /* String AdMobTop = Vidgyor.setBannerAdsTop(ChannelName);
        String AdMobBottom = Vidgyor.setBannerAdsBottom(ChannelName);

        MobileAds.initialize(this, AdMobTop);
        MobileAds.initialize(this, AdMobBottom);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView_top.loadAd(adRequest);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView_bottom.loadAd(adRequest1);*/

       getSupportActionBar().hide();

        AdRequest adRequest_top = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("4DD0986B8BB49093161F4F00CF61B887")// Add your real device id here
                .build();
        adView_top.loadAd(adRequest_top);

        AdRequest adRequest_bottom = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("4DD0986B8BB49093161F4F00CF61B887")// Add your real device id here
                .build();
        adView_bottom.loadAd(adRequest_bottom);
    }

    class WvClient extends WebViewClient
    {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
            // Ignore SSL certificate errors
        }
    }

}
