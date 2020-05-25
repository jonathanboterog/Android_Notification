package com.ingenico.petagram.model;

import android.content.ContentValues;
import android.content.Context;

import com.ingenico.petagram.R;
import com.ingenico.petagram.db.DataBase;
import com.ingenico.petagram.db.DataBaseConstants;

import java.util.ArrayList;

public class PetConstructor {

    private static final int RATING = 1;
    private Context context;

    public PetConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getData(){
        DataBase db = new DataBase(context);
        if(db.isEmptyDatabase())
            InsertFivePets(db);
        return db.getAllPets();
    }

    public void InsertFivePets(DataBase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Eyes");
        contentValues.put(DataBaseConstants.TABLE_PETS_PHOTO, R.drawable.cateyes);
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Fight");
        contentValues.put(DataBaseConstants.TABLE_PETS_PHOTO, R.drawable.catfight);
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Moustache");
        contentValues.put(DataBaseConstants.TABLE_PETS_PHOTO, R.drawable.catmoustache);
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Grumpy");
        contentValues.put(DataBaseConstants.TABLE_PETS_PHOTO, R.drawable.catgrumpy);
        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Tied");
        contentValues.put(DataBaseConstants.TABLE_PETS_PHOTO, R.drawable.cattied);
        db.insertPet(contentValues);
    }

    public void setRatingPet(Pet pet){
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_RATING_PET_ID, pet.getId());
        contentValues.put(DataBaseConstants.TABLE_RATING_COUNT, RATING);
        db.insertRatingPet(contentValues);
    }

    public int getRatingPet(Pet pet){
        DataBase db = new DataBase(context);
        return db.getRatingPet(pet);
    }
}
