package com.ingenico.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.ingenico.petagram.model.Pet;

import java.util.ArrayList;

public class PetHistorical extends AppCompatActivity {

    private ArrayList<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pet_info);

        Toolbar toolbar = findViewById(R.id.incActionbarInfo);
        RelativeLayout lyStar = findViewById(R.id.lyStar);
        lyStar.setVisibility(View.INVISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra(getString(R.string.pbundle));
        if(args != null)
            pets = (ArrayList<Pet>)  args.getSerializable(getString(R.string.ppets));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
