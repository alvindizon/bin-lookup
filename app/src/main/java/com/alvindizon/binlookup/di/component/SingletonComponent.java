package com.alvindizon.binlookup.di.component;

import android.content.Context;

import com.alvindizon.binlookup.di.module.ApplicationModule;
import com.alvindizon.binlookup.di.module.NetworkModule;
import com.alvindizon.binlookup.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class,
        NetworkModule.class})
@Singleton
public interface SingletonComponent {
    ViewModelComponent viewModelComponent(ViewModelModule viewModelModule);
}
