package com.ingenico.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingenico.petagram.restApi.ConstRestApi;
import com.ingenico.petagram.restApi.ConstRestApiServer;
import com.ingenico.petagram.restApi.IEndPointsApi;
import com.ingenico.petagram.restApi.IEndpointServer;
import com.ingenico.petagram.restApi.model.PetResponse;
import com.ingenico.petagram.restApi.unserializer.PetUnserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiServerAdapter {

    //dont receive parameter, because model class is equal to response of WS
    public IEndpointServer makeConnectionRestApiServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstRestApiServer.URL_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IEndpointServer.class);
    }
}

