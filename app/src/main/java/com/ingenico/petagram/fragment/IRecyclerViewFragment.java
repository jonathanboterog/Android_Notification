package com.ingenico.petagram.fragment;

import com.ingenico.petagram.adapter.PetAdapter;
import com.ingenico.petagram.model.Pet;

import java.util.ArrayList;

public interface IRecyclerViewFragment {

    void GenerateVerticalLinearLayout();

    PetAdapter createAdapter(ArrayList<Pet> pets);

    void initAdapter(PetAdapter adapter);
}
