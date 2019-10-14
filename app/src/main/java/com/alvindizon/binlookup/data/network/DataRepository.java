package com.alvindizon.binlookup.data.network;

import com.alvindizon.binlookup.data.network.api.ApiService;
import com.alvindizon.binlookup.data.network.response.APIResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataRepository {

    private final ApiService apiService;

    @Inject
    public DataRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<APIResponse> lookup(String bin) {
        return apiService.lookup(bin);
    }
}
