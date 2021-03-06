package com.alvindizon.binlookup.di.module;

import androidx.lifecycle.ViewModel;

import com.alvindizon.binlookup.core.ViewModelFactory;
import com.alvindizon.binlookup.data.network.DataRepository;
import com.alvindizon.binlookup.data.network.api.ApiService;
import com.alvindizon.binlookup.features.lookup.MainViewModel;
import com.squareup.moshi.Moshi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory provideViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel provideMainViewModel(DataRepository dataRepository) {
        return new MainViewModel(dataRepository);
    }

}
