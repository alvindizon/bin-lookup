package com.alvindizon.binlookup.di.component;

import com.alvindizon.binlookup.di.module.ViewModelModule;
import com.alvindizon.binlookup.features.lookup.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ViewModelModule.class)
public interface ViewModelComponent {
    void inject(MainActivity activity);
}
