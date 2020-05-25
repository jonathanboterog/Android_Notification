package com.ingenico.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.R;
import com.ingenico.petagram.model.PetConstructor;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ContactViewHolder> {

    private ArrayList<Pet> pets;
    private Activity activity;

    //Construye lista de contactos
    public PetAdapter(ArrayList<Pet> pets, Activity activity){
        this.pets = pets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //da vida al layout. Inflar el layout y lo pasara a viewholder para que el obtenga los views.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder contactViewHolder, int position) {
        //Donde se pasa lista de contactos. Asocia cada elemento de nuestra lista a cada view.
        final Pet pet = pets.get(position);
        contactViewHolder.imgContactPhoto.setImageResource(pet.getPhoto());
        contactViewHolder.tvName.setText(pet.getName());
        contactViewHolder.tvRating.setText(String.valueOf(pet.getRating()));

        contactViewHolder.imgContactPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, pet.getName(), Snackbar.LENGTH_SHORT).show();
            }
        });

        contactViewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, "Rating to " + pet.getName(),
                        Toast.LENGTH_SHORT).show();

                PetConstructor petConstructor = new PetConstructor(activity);
                petConstructor.setRatingPet(pet);
                contactViewHolder.tvRating.setText(String.valueOf(petConstructor.getRatingPet(pet)));
//                contactViewHolder.tvRating.setText(petConstructor.getRatingPet(pet) + " " + activity.getString(R.string.rating));
//
//                pet.setRating(pet.getRating() + 1);
//                contactViewHolder.tvRating.setText(String.valueOf(pet.getRating()));
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
        private ImageView imgContactPhoto;
        private TextView tvName;
        private TextView tvRating;
        private ImageView imgLike;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgContactPhoto = itemView.findViewById(R.id.imgContactPhoto);
            tvName = itemView.findViewById(R.id.tvName);
            tvRating = itemView.findViewById(R.id.tvRating);
            imgLike = itemView.findViewById(R.id.imgLike);
        }
    }
}
