package com.ingenico.petagram.restApi;

import com.ingenico.petagram.restApi.model.PetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IEndPointsApi {

    @GET(ConstRestApi.URL_GET_RECENT_MEDIA)
    Call<PetResponse> getRecentMedia();
}
