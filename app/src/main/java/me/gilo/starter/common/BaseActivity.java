package me.gilo.starter.common;

import android.annotation.SuppressLint;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;


/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }
}
