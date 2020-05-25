package com.ingenico.petagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.ingenico.petagram.PetHistorical;
import com.ingenico.petagram.PhotoDetail;
import com.ingenico.petagram.R;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.model.PetApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.ContactViewHolder> {
    private ArrayList<PetApi> pets;
    private Activity activity;

    //Construye lista de contactos
    public PetProfileAdapter(ArrayList<PetApi> pets, Activity activity){
        this.pets = pets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetProfileAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //da vida al layout. Inflar el layout y lo pasara a viewholder para que el obtenga los views.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet_profile, parent, false);
        return new PetProfileAdapter.ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PetProfileAdapter.ContactViewHolder contactViewHolder, int position) {
        //Donde se pasa lista de contactos. Asocia cada elemento de nuestra lista a cada view.
        final PetApi pet = pets.get(position);
        Picasso.get().load(pet.getPhotoUrl()).into(contactViewHolder.imgProfContactPhoto);
        contactViewHolder.tvProfRating.setText("Id: " + String.valueOf(pet.getId_str()));

        contactViewHolder.imgProfContactPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PhotoDetail.class);
                intent.putExtra(activity.getString(R.string.pDetailUrl), pet.getCaption());
                intent.putExtra(activity.getString(R.string.pDetailPhoto), pet.getPhotoUrl());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //retorna cantidad de elementos de nuestra lista
        return pets.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        //Will be defined all views of card-view layout
        private ImageView imgProfContactPhoto;
        private TextView tvProfRating;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfContactPhoto = itemView.findViewById(R.id.imgProfContactPhoto);
            tvProfRating = itemView.findViewById(R.id.tvProfRating);
        }
    }
}
