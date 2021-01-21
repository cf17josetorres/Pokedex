package com.example.pokedex;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        if(getSupportActionBar() != null)

            getSupportActionBar().hide();

        video = (VideoView) findViewById(R.id.videoPromocion);

        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.promo));

        MediaController media = new MediaController(this);

        video.setMediaController(media);

        video.start();

    }
}