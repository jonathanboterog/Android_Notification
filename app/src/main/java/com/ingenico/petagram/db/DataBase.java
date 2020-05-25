package com.ingenico.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ingenico.petagram.model.Pet;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(@Nullable Context context) {
        super(context, DataBaseConstants.DB_NAME, null, DataBaseConstants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTablePets = "CREATE TABLE " + DataBaseConstants.TABLE_PETS + "(" +
                DataBaseConstants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_PETS_NAME + " TEXT, " +
                DataBaseConstants.TABLE_PETS_PHOTO + " INTEGER)";

        String queryCreateTableRating = "CREATE TABLE " + DataBaseConstants.TABLE_RATING + "(" +
                DataBaseConstants.TABLE_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_RATING_PET_ID + " INTEGER, " +
                DataBaseConstants.TABLE_RATING_COUNT + " INTEGER, " +
                "FOREIGN KEY (" + DataBaseConstants.TABLE_RATING_PET_ID + ") " +
                "REFERENCES " + DataBaseConstants.TABLE_PETS + "(" + DataBaseConstants.TABLE_PETS_ID + ")" +
                ")";

        db.execSQL(queryCreateTablePets);
        db.execSQL(queryCreateTableRating);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_RATING);
        onCreate(db);
    }

    public boolean isEmptyDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + DataBaseConstants.TABLE_PETS, null);
        if (cur != null){
            cur.moveToFirst();
            if (cur.getInt(0) == 0)
                return true;
        }
        return false;
    }

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query, null);

        while (registers.moveToNext()){
            Pet currPet = new Pet();
            currPet.setId(registers.getInt(0));
            currPet.setName(registers.getString(1));
            currPet.setPhoto(registers.getInt(2));

            String queryRating = "SELECT COUNT("+ DataBaseConstants.TABLE_RATING_COUNT +") as likes " +
                    " FROM " + DataBaseConstants.TABLE_RATING +
                    " WHERE " + DataBaseConstants.TABLE_RATING_PET_ID + "=" + currPet.getId();

            Cursor registersRating = db.rawQuery(queryRating, null);
            if (registersRating.moveToNext()){
                currPet.setRating(registersRating.getInt(0));
            }else {
                currPet.setRating(0);
            }

            registersRating.close();
            pets.add(currPet);

        }
        registers.close();
        db.close();

        return pets;
    }

    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_PETS,null, contentValues);
        db.close();
    }

    public void insertRatingPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_RATING, null, contentValues);
        db.close();
    }


    public int getRatingPet(Pet pet){
        int rating = 0;

        String query = "SELECT COUNT("+ DataBaseConstants.TABLE_RATING_COUNT +") as likes " +
                " FROM " + DataBaseConstants.TABLE_RATING +
                " WHERE " + DataBaseConstants.TABLE_RATING_PET_ID + "=" + pet.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query, null);

        if (registers.moveToNext()){
            rating = registers.getInt(0);
        }

        registers.close();
        db.close();

        return rating;
    }
}
