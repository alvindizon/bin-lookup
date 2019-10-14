package com.alvindizon.binlookup.features.lookup;

import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alvindizon.binlookup.data.network.DataRepository;
import com.alvindizon.binlookup.data.network.response.APIResponse;
import com.alvindizon.binlookup.data.network.status.Status;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    public static final String TAG = MainViewModel.class.getSimpleName();

    private final DataRepository dataRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Status> networkStatus = new MutableLiveData<>(Status.NONE);
    public ObservableField<BinInfo> binInfo = new ObservableField<>(new BinInfo());

    @Inject
    public MainViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void lookupBin(String bin) {
        compositeDisposable.add(dataRepository.lookup(bin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::transformToUi,
                error -> {
                    error.printStackTrace();
                    networkStatus.setValue(Status.ERROR);
                }
            )
        );
    }

    private void transformToUi(APIResponse data) {
        BinInfo binInfo = new BinInfo();
        if(data == null) {
            networkStatus.setValue(Status.ERROR);
        }
        if(!TextUtils.isEmpty(data.getScheme())) {
            binInfo.setScheme(data.getScheme());
        }

        if(!TextUtils.isEmpty(data.getType())) {
            binInfo.setType(data.getType());
        }

        if(!TextUtils.isEmpty(data.getBrand())) {
            binInfo.setBrand(data.getBrand());
        }
        if(data.getCountry() != null) {
            if(!TextUtils.isEmpty(data.getCountry().getName())) {
                binInfo.setCountry(data.getCountry().getName());
            }
        }
        if(data.getBank() != null) {
            if(!TextUtils.isEmpty(data.getBank().getName())) {
                binInfo.setBank(data.getBank().getName());
            }
        }
        if(data.getNumber() != null) {
            if(data.getNumber().getLength() != null) {
                binInfo.setLength(data.getNumber().getLength().toString());
            }
        }
        this.binInfo.set(binInfo);
        this.binInfo.notifyChange();
        networkStatus.setValue(Status.OK);
    }

    public LiveData<Status> getNetworkStatus() {
        return networkStatus;
    }
}
