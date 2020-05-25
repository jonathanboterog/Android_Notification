package com.ingenico.petagram.presenter;
import android.content.Context;
import com.ingenico.petagram.fragment.IRecyclerViewFragment;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.model.PetConstructor;
import java.util.ArrayList;

public class RecyclerViewFragmentPresenter {

    private IRecyclerViewFragment iRecyclerViewFragment;
    private Context context;
    private ArrayList<Pet> pets;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragment iRecyclerViewFragment, Context context) {
        this.iRecyclerViewFragment = iRecyclerViewFragment;
        this.context = context;
        getPets();
    }

    public void getPets(){
        PetConstructor petConstructor = new PetConstructor(context);
        pets = petConstructor.getData();
        showPets();
    }

    public void showPets(){
        this.iRecyclerViewFragment.initAdapter(this.iRecyclerViewFragment.createAdapter(pets));
        this.iRecyclerViewFragment.GenerateVerticalLinearLayout();
    }
}
