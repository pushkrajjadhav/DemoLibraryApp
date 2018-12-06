package com.codeplateau.demolibraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.codeplateau.vidgyor.LiveTvFullScreenActivity;
import com.codeplateau.vidgyor.Vidgyor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vidgyor.init(this);

        Button btnLiveTV = (Button) findViewById(R.id.btnLiveTV);

        btnLiveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LiveTvFullScreenActivity.class);
                intent.putExtra("ChannelName", "asianet");
                startActivity(intent);
            }
        });


    }
}
