package com.ingenico.petagram.restApi;

public final class ConstRestApi {

    public static final String URL_ROOT = "https://graph.instagram.com/";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String KEY_FIELDS = "?fields=";

    public static final String KEY_FIELDS_VALUES = "id,media_url,caption";
    public static final String KEY_ACCESS_TOKEN_VALUE = "IGQVJVZAEJzSmx2X0l1bjJkWUw0ZA2xJSWFfeW0yUHBBd2tEMWhUZA29lekliM0ZApWmxPS2hWWUdLcE1YNktFNEpndF9JS0hXVXBFaTVjNVpFaVg4YlBRbVd0ODBNbGY3c3ZAoWTREaVRDZAk9FWDIxZAUc0SQZDZD";

    public static final String GET_INFO_MEDIA = "me/media";

    public static final String URL_GET_RECENT_MEDIA = GET_INFO_MEDIA +
            KEY_FIELDS + KEY_FIELDS_VALUES +
            KEY_ACCESS_TOKEN + KEY_ACCESS_TOKEN_VALUE;

}
