package com.ingenico.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingenico.petagram.R;
import com.ingenico.petagram.adapter.PetAdapter;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragment{

    static private ArrayList<Pet> pets;
    private RecyclerView contactList;
    private RecyclerViewFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_recyclerview, container, false);
        contactList = v.findViewById(R.id.rcvContacts);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return v;
    }

    @Override
    public void GenerateVerticalLinearLayout() {
        //Define form to show recyclerView
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        contactList.setLayoutManager(llm);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pet> pets) {
        PetAdapter adapter = new PetAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public void initAdapter(PetAdapter adapter) {
        contactList.setAdapter(adapter);
    }
}
