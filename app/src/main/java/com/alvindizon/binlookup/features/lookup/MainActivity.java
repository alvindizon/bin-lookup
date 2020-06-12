package com.alvindizon.binlookup.features.lookup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alvindizon.binlookup.core.ViewModelFactory;
import com.alvindizon.binlookup.data.network.status.Status;
import com.alvindizon.binlookup.databinding.ActivityMainBinding;
import com.alvindizon.binlookup.di.Injector;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Injector.getViewModelComponent().inject(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        viewModel.getNetworkStatus().observe(this, status -> {
            if(status == Status.ERROR) {
                Toast.makeText(this, "Error fetching results.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.lookupBtn.setOnClickListener(v -> {
            if(!TextUtils.isEmpty(binding.bin.getText().toString())) {
                viewModel.lookupBin(binding.bin.getText().toString());
            }
        });
    }
}
