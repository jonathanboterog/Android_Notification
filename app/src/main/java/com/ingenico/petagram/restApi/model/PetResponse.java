package com.ingenico.petagram.restApi.model;
import com.ingenico.petagram.model.PetApi;

import java.util.ArrayList;

public class PetResponse {

    ArrayList<PetApi> pets;

    public ArrayList<PetApi> getPets() {
        return pets;
    }

    public void setPets(ArrayList<PetApi> pets) {
        this.pets = pets;
    }

}
