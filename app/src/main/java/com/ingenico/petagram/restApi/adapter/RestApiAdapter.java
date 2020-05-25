package com.ingenico.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingenico.petagram.restApi.ConstRestApi;
import com.ingenico.petagram.restApi.IEndPointsApi;
import com.ingenico.petagram.restApi.model.PetResponse;
import com.ingenico.petagram.restApi.unserializer.PetUnserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public IEndPointsApi makeConnectionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstRestApi.URL_ROOT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IEndPointsApi.class);
    }

    public Gson BuildGsonUnserializerRecentMedia(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetUnserializer());
        return gsonBuilder.create();
    }
}
