package com.alvindizon.binlookup.features.lookup;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alvindizon.binlookup.core.ViewModelFactory;
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
        viewModel.getUiStatus().observe(this, this::handleStatus);

        binding.lookupBtn.setOnClickListener(v -> {
            if(!TextUtils.isEmpty(binding.bin.getText().toString())) {
                viewModel.lookupBin(binding.bin.getText().toString());
            }
        });
    }

    private void handleStatus(UIState status) {
        switch (status.getStatus()) {
            case LOADING:
                binding.veilLayout.setVisibility(View.VISIBLE);
                binding.errorText.setVisibility(View.INVISIBLE);
                binding.veilLayout.veil();
                break;
            case SUCCESS:
                binding.veilLayout.setVisibility(View.VISIBLE);
                setResultToUi(status.getBinInfo());
                binding.veilLayout.unVeil();
                break;
            case ERROR:
                binding.veilLayout.setVisibility(View.INVISIBLE);
                binding.errorText.setVisibility(View.VISIBLE);
                binding.errorText.setText(status.getErrorMessage());
                break;
            default:
                break;
        }
    }

    private void setResultToUi(BinInfo binInfo) {
        binding.scheme.setText(binInfo.getScheme());
        binding.type.setText(binInfo.getType());
        binding.brand.setText(binInfo.getBrand());
        binding.country.setText(binInfo.getCountry());
        binding.bank.setText(binInfo.getBank());
        binding.length.setText(binInfo.getLength());
    }
}
