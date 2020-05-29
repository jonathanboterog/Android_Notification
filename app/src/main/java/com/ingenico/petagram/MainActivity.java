package com.ingenico.petagram;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.ingenico.petagram.OptionMenu.About;
import com.ingenico.petagram.OptionMenu.Account;
import com.ingenico.petagram.OptionMenu.ContactForm;
import com.ingenico.petagram.adapter.PageAdapter;
import com.ingenico.petagram.fragment.ProfileFragment;
import com.ingenico.petagram.fragment.RecyclerViewFragment;
import com.ingenico.petagram.restApi.IEndpointServer;
import com.ingenico.petagram.restApi.adapter.RestApiServerAdapter;
import com.ingenico.petagram.restApi.model.TokenResponse;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout lyStar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SharedPreferences pref;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences(getString(R.string.mySharedPref), MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.incActionbarMain);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        lyStar = findViewById(R.id.lyStar);
        lyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PetHistorical.class);
                startActivity(intent);
            }
        });

        SetUpViewPager();
    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new ProfileFragment());

        return fragments;
    };

    public void SetUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.profile_tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch(item.getItemId()){

            case R.id.action_contact:
                intent = new Intent(this, ContactForm.class);
                startActivity(intent);
                break;

            case R.id.action_about:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;

            case R.id.action_account:
                intent = new Intent(this, Account.class);
                startActivity(intent);
                break;

            case R.id.action_notifications:
                username = pref.getString(getString(R.string.key_account), "");

                if(username.length() <= 0)
                {
                    Toast.makeText(this, "Primero Configure una cuenta!", Toast.LENGTH_SHORT).show();
                    break;
                }

                RegisterNotifications();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void RegisterNotifications(){

        String TAG = "Notification_firebase";

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        RestApiServerAdapter restApiAdapter = new RestApiServerAdapter();
                        IEndpointServer endpoint = restApiAdapter.makeConnectionRestApiServer();
                        Call<TokenResponse> tokenResponseCall = endpoint.registerDeviceID(token, username);

                        tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                            @Override
                            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                                Log.d("FIREBASE - STRING", response.body().toString());
                                TokenResponse tokenResponse = response.body();
                                Log.d("FIREBASE - ID", tokenResponse.getId());
                                Log.d("FIREBASE - DEVICE ID", tokenResponse.getId_dispositivo());
                                Log.d("FIREBASE - USER ID", tokenResponse.getId_usuario_instagram());
                            }

                            @Override
                            public void onFailure(Call<TokenResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Error conexion Webservices!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        // Log and toast
                        String msg = "Token: " + token;
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, "Dispositivo Registrado :)", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
