package com.ingenico.petagram.OptionMenu;

import com.ingenico.petagram.R;
import com.ingenico.petagram.mail.SendMail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ContactForm extends AppCompatActivity {

    private String name;
    private String email;
    private String message;
    //put your email here
    private final String mailPetagram = "jonathanboterog@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        Toolbar toolbar = findViewById(R.id.incActionbarForm);
        RelativeLayout lyStar = findViewById(R.id.lyStar);
        lyStar.setVisibility(View.INVISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    private void sendEmail() {
        //Creating SendMail object
        SendMail sm = new SendMail(this, mailPetagram, name + " - " + email,
                message);

        //Executing sendmail to send email
        sm.execute();
    }

    public void OnClickSend(View view) {

        EditText edt_name = findViewById(R.id.edt_name);
        name = edt_name.getText().toString().trim();

        EditText edt_email = findViewById(R.id.edt_email);
        email = edt_email.getText().toString().trim();

        EditText edt_message = findViewById(R.id.edt_message);
        message = edt_message.getText().toString().trim();

        //using java-mail Lib... but I think this is insecure because ask email password :(
        //sendEmail();

        //using intent, no security problem
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mailPetagram});
        i.putExtra(Intent.EXTRA_SUBJECT, name + " - " + email);
        i.putExtra(Intent.EXTRA_TEXT   , message);
        try {
            startActivity(Intent.createChooser(i, getString(R.string.bt_send)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No hay app de correo instalada!", Toast.LENGTH_SHORT).show();
        }
    }
}
