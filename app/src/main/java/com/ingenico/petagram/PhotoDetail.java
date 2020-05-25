package com.ingenico.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetail extends AppCompatActivity {

    private ImageView imgDetailPhoto;
    private TextView tvDetailRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        Toolbar toolbar = findViewById(R.id.incActionbarDetail);
        RelativeLayout lyStar = findViewById(R.id.lyStar);
        lyStar.setVisibility(View.INVISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String caption = extras.getString(getString(R.string.pDetailUrl));
        String PhotoUrl = extras.getString(getString(R.string.pDetailPhoto));

        imgDetailPhoto = findViewById(R.id.imgDetailPhoto);
        tvDetailRating = findViewById(R.id.tvDetailRating);

        tvDetailRating.setText(String.valueOf(caption));
        Picasso.get().load(PhotoUrl).into(imgDetailPhoto);
    }
}
