package com.alvindizon.binlookup.application;

import android.app.Application;

import com.alvindizon.binlookup.di.component.DaggerSingletonComponent;
import com.alvindizon.binlookup.di.component.SingletonComponent;
import com.alvindizon.binlookup.di.component.ViewModelComponent;
import com.alvindizon.binlookup.di.module.ApplicationModule;
import com.alvindizon.binlookup.di.module.ViewModelModule;

public class BinLookupApp extends Application {
    private static BinLookupApp INSTANCE;

    SingletonComponent singletonComponent;
    ViewModelComponent viewModelComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        singletonComponent = DaggerSingletonComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        viewModelComponent = singletonComponent.viewModelComponent(new ViewModelModule());
    }

    public static BinLookupApp get() {
        return INSTANCE;
    }

    public SingletonComponent getSingletonComponent() {
        return singletonComponent;
    }

    public ViewModelComponent getViewModelComponent() {
        return viewModelComponent;
    }
}

