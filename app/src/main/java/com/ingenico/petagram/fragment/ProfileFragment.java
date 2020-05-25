package com.ingenico.petagram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ingenico.petagram.R;
import com.ingenico.petagram.adapter.PetProfileAdapter;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.model.PetApi;
import com.ingenico.petagram.presenter.ProfileFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements IProfileFragment{
    static private ArrayList<Pet> pets;
    private RecyclerView contactList;
    private ProfileFragmentPresenter presenter;
    private SwipeRefreshLayout swipe_container;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        contactList = v.findViewById(R.id.rcvProfile);
        presenter = new ProfileFragmentPresenter(this, getContext());

        swipe_container = v.findViewById(R.id.swipe_container);
        swipe_container.setOnRefreshListener(() -> RefreshContent());
        return v;
    }

    @Override
    public void GenerateGridLayout() {
        //Define form to show recyclerView
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        glm.setOrientation(RecyclerView.VERTICAL);
        contactList.setLayoutManager(glm);
    }

    @Override
    public PetProfileAdapter createAdapter(ArrayList<PetApi> pets) {
        PetProfileAdapter adapter = new PetProfileAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public void initAdapter(PetProfileAdapter adapter) {
        contactList.setAdapter(adapter);
    }

    @Override
    public void RefreshContent() {
        presenter = new ProfileFragmentPresenter(this, getContext());
        swipe_container.setRefreshing(false);
    }
}
