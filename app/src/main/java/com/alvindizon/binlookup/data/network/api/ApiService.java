package com.alvindizon.binlookup.data.network.api;

import com.alvindizon.binlookup.data.network.response.APIResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface ApiService {
    @Headers({"Accept-Version: 3"})
    @GET()
    Observable<APIResponse> lookup(@Url String bin);
}
