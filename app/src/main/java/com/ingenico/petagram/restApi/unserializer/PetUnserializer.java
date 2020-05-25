package com.ingenico.petagram.restApi.unserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ingenico.petagram.model.Pet;
import com.ingenico.petagram.model.PetApi;
import com.ingenico.petagram.restApi.JsonKeys;
import com.ingenico.petagram.restApi.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PetUnserializer implements JsonDeserializer<PetResponse> {

    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESP_ARRAY);

        petResponse.setPets(unserializeJsonPet(petResponseData));

        return petResponse;
    }

    private ArrayList<PetApi> unserializeJsonPet(JsonArray petResponseData){
        ArrayList<PetApi> pets = new ArrayList<>();
        for(int i = 0; i < petResponseData.size(); i++){
            JsonObject petReponseDataObject = petResponseData.get(i).getAsJsonObject();

            String id = "";
            String caption = "";
            String photoUrl = "";

            if(petReponseDataObject.get(JsonKeys.MEDIA_USER_ID) != null)
                id = petReponseDataObject.get(JsonKeys.MEDIA_USER_ID).getAsString();
            if(petReponseDataObject.get(JsonKeys.MEDIA_CAPTION) != null)
                caption = petReponseDataObject.get(JsonKeys.MEDIA_CAPTION).getAsString();
            if(petReponseDataObject.get(JsonKeys.MEDIA_PHOTO_URL) != null)
                photoUrl = petReponseDataObject.get(JsonKeys.MEDIA_PHOTO_URL).getAsString();

            PetApi petCurr = new PetApi();
            petCurr.setId_str(id);
            petCurr.setCaption(caption);
            petCurr.setPhotoUrl(photoUrl);

            pets.add(petCurr);
        }

        return pets;
    }
}
