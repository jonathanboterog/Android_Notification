package com.ingenico.petagram.fragment;

import com.ingenico.petagram.adapter.PetProfileAdapter;
import com.ingenico.petagram.model.PetApi;

import java.util.ArrayList;

public interface IProfileFragment {

    void GenerateGridLayout();

    PetProfileAdapter createAdapter(ArrayList<PetApi> pets);

    void initAdapter(PetProfileAdapter adapter);

    void RefreshContent();
}
