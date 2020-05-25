package com.ingenico.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ingenico.petagram.R;
import com.ingenico.petagram.fragment.IProfileFragment;
import com.ingenico.petagram.model.PetApi;
import com.ingenico.petagram.restApi.IEndPointsApi;
import com.ingenico.petagram.restApi.adapter.RestApiAdapter;
import com.ingenico.petagram.restApi.model.PetResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter {

    private IProfileFragment iProfileFragment;
    private Context context;
    private ArrayList<PetApi> petsApi;

    public ProfileFragmentPresenter(IProfileFragment iProfileFragment, Context context) {
        this.iProfileFragment = iProfileFragment;
        this.context = context;
        getBasicMediaInstagram();
    }

    public void showPetsMedia(){
        this.iProfileFragment.initAdapter(this.iProfileFragment.createAdapter(petsApi));
        this.iProfileFragment.GenerateGridLayout();
    }

    public void getBasicMediaInstagram(){
        /*Object type RestApiAdapter -> First, establish server connection to Instagram API and
        return a result in Json format.*/
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        /*Configure format that we want deserialize the data. Retrofit localize a class with same
        format*/
        Gson gsonMediaRecent = restApiAdapter.BuildGsonUnserializerRecentMedia();
        /*Establishe connection and pass the object that we want unserialize the response*/
        IEndPointsApi iendPointsApi = restApiAdapter.makeConnectionRestApiInstagram(gsonMediaRecent);
        /*Begin the query to Endpoint, with all automatic process.*/
        Call<PetResponse> petResponseCall = iendPointsApi.getRecentMedia();

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                petsApi = petResponse.getPets();
                showPetsMedia();
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, R.string.api_cnx_fail, Toast.LENGTH_LONG).show();
                Log.e("CONNECTION FAIL", t.toString());
            }
        });
    }
}
