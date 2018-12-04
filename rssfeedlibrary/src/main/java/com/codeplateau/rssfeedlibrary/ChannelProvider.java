package com.codeplateau.rssfeedlibrary;

public class ChannelProvider {

    static ChannelItem channelItem = new ChannelItem();

    public static String setChannelName(String channelname){

        channelItem.setChannel_name(channelname);
        return channelname;
    }

    public static String setUrl(String url){

        channelItem.setWebview_url(url);
        return url;
    }
}
