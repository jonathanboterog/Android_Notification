package com.ingenico.petagram.restApi;

import com.ingenico.petagram.restApi.model.PetResponse;
import com.ingenico.petagram.restApi.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IEndpointServer {

    @FormUrlEncoded
    @POST(ConstRestApiServer.KEY_POST_REGISTER_USER)
    Call<TokenResponse> registerDeviceID(@Field(ConstRestApiServer.KEY_FIELD_ID_DEVICE) String idDevice,
                                        @Field(ConstRestApiServer.KEY_FIELD_ID_USER) String idUser);
}
