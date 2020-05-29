package com.ingenico.petagram.OptionMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ingenico.petagram.MainActivity;
import com.ingenico.petagram.R;

public class Account extends AppCompatActivity {

    private SharedPreferences pref;
    private EditText edt_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        pref = getApplicationContext().getSharedPreferences(getString(R.string.mySharedPref), MODE_PRIVATE); // 0 - for private mode

        Toolbar toolbar = findViewById(R.id.incActionbarAccount);
        RelativeLayout lyStar = findViewById(R.id.lyStar);
        lyStar.setVisibility(View.INVISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String account = pref.getString(getString(R.string.key_account), "");

        edt_user = findViewById(R.id.edt_user);
        edt_user.setText(account);
    }

    public void OnClickSaveAccount(View view) {

        String account = edt_user.getText().toString().trim();
        if(account.length() <= 0)
        {
            Toast.makeText(this, "Llenar Campo usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(getString(R.string.key_account), account);
        editor.commit();

        Toast.makeText(view.getContext(),"Cuenta configurada !", Toast.LENGTH_SHORT).show();

        finish();
    }
}
