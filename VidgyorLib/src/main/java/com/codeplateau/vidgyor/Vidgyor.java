package com.codeplateau.vidgyor;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Vidgyor {

    public static String responseJson = "";

    static ArrayList<ChannelItem> channelItemArrayList = new ArrayList<>();

    public static void init(Context context) {
        //showpDialog();

        TrustingOkClient ok = new TrustingOkClient();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConfig.url_data)
                .setClient(ok)
                .build();

        RetroAPI retApi = adapter.create(RetroAPI.class);
        retApi.url_getdata(new Callback<Response>() {
            @Override
            public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                responseJson = getStringResponse(response);
                //hidepDialog();

                channelItemArrayList = handleNewsResponse(responseJson);
                System.out.println("response ===" + responseJson);
            }

            @Override
            public void failure(RetrofitError error) {
                //hidepDialog();
                System.out.println("test error ==" + error);
                /*Toast toast = Toast.makeText(this, "Server error occured", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                        0, 0);
                toast.show();*/
            }
        });
    }

    public static String getStringResponse(retrofit.client.Response result) {

        //Try to get response body
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = sb.toString();

        return response;
    }

    public static ArrayList<ChannelItem> handleNewsResponse(String responseJson) {
        if (responseJson != null) {

            try {

                JSONObject jObj = new JSONObject(responseJson);
                JSONArray jsonArray = jObj.getJSONArray("channels");

                for (int i = 0; i < jsonArray.length(); i++) {

                    ChannelItem channelItem = new ChannelItem();

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    channelItem.setChannel_name(jsonObject.optString("channel_name"));
                    channelItem.setWebview_url(jsonObject.optString("webview_url"));

                    channelItemArrayList.add(channelItem);

                    Log.d("response :", channelItemArrayList.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //Toast.makeText(this, "Response Getting Empty!!!", Toast.LENGTH_SHORT).show();
        }

        return channelItemArrayList;
    }

    public static void setWebView(String ChannelName, WebView webView){

        if(!channelItemArrayList.isEmpty()){

            for (int i = 0; i < channelItemArrayList.size(); i++) {

                String channelName1 = channelItemArrayList.get(i).getChannel_name();
                if(ChannelName.equals(channelName1)){
                    webView.loadUrl(channelItemArrayList.get(i).getWebview_url());
                }
            }
        } else {

            Log.d("response: ", "No channel found!");
        }
    }

    public static String setBannerAdsTop(String ChannelName){

        String AdMobTop = "";

        if(!channelItemArrayList.isEmpty()){

            for (int i = 0; i < channelItemArrayList.size(); i++) {

                String channelName1 = channelItemArrayList.get(i).getChannel_name();
                if(ChannelName.equals(channelName1)){

                    AdMobTop = channelItemArrayList.get(i).getLivetv_top_banner_id_admob();
                }
            }
        } else {

            Log.d("response: ", "No channel found!");
        }

        return AdMobTop;
    }

    public static String setBannerAdsBottom(String ChannelName){

        String AdMobBottom = "";

        if(!channelItemArrayList.isEmpty()){

            for (int i = 0; i < channelItemArrayList.size(); i++) {

                String channelName1 = channelItemArrayList.get(i).getChannel_name();
                if(ChannelName.equals(channelName1)){

                    AdMobBottom = channelItemArrayList.get(i).getLivetv_bottom_banner_id_admob();
                }
            }
        } else {

            Log.d("response: ", "No channel found!");
        }

        return AdMobBottom;
    }
}
