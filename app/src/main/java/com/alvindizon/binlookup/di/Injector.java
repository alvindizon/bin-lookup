package com.alvindizon.binlookup.di;

import com.alvindizon.binlookup.application.BinLookupApp;
import com.alvindizon.binlookup.di.component.SingletonComponent;
import com.alvindizon.binlookup.di.component.ViewModelComponent;

public class Injector {
    public static SingletonComponent get() {
        return BinLookupApp.get().getSingletonComponent();
    }

    public static ViewModelComponent getViewModelComponent() {
        return BinLookupApp.get().getViewModelComponent();
    }
}
