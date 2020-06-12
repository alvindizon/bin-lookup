package com.alvindizon.binlookup.features.lookup;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alvindizon.binlookup.data.network.DataRepository;
import com.alvindizon.binlookup.data.network.response.APIResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MainViewModel extends ViewModel {

    private final DataRepository dataRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<UIState> uiStatus = new MutableLiveData<>();

    @Inject
    public MainViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void lookupBin(String bin) {
        compositeDisposable.add(dataRepository.lookup(bin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(disposable -> uiStatus.setValue(UIState.LOADING()))
            .subscribe(this::transformToBinInfo,
                error -> {
                    error.printStackTrace();
                    handleError(error);
                }
            )
        );
    }

    private void handleError(Throwable error) {
        if(error instanceof HttpException) {
            int statusCode = ((HttpException) error).code();
            switch (statusCode) {
                case 404:
                    uiStatus.setValue(UIState.ERROR("BIN not found."));
                    break;
                default:
                    break;
            }
        } else {
            uiStatus.setValue(UIState.ERROR(error.getMessage()));
        }
    }

    private void transformToBinInfo(APIResponse data) {
        BinInfo binInfo = new BinInfo();

        if(!TextUtils.isEmpty(data.getScheme())) {
            binInfo.setScheme(data.getScheme());
        }

        if(!TextUtils.isEmpty(data.getType())) {
            binInfo.setType(data.getType());
        }

        if(!TextUtils.isEmpty(data.getBrand())) {
            binInfo.setBrand(data.getBrand());
        }
        if(!TextUtils.isEmpty(data.getCountry().getName())) {
            binInfo.setCountry(data.getCountry().getName());
        }
        if(!TextUtils.isEmpty(data.getBank().getName())) {
            binInfo.setBank(data.getBank().getName());
        }
        if(data.getNumber() != null) {
            if(data.getNumber().getLength() != null) {
                binInfo.setLength(data.getNumber().getLength().toString());
            }
        }

        uiStatus.setValue(UIState.SUCCESS(binInfo));
    }

    public LiveData<UIState> getUiStatus() {
        return uiStatus;
    }
}
