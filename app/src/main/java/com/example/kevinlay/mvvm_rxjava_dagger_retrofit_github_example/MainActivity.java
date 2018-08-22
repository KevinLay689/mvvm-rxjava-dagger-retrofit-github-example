package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.retrofit.RetrofitMode;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.retrofit.RetrofitModule;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.GithubFragmentViewModel;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.GithubFragmentViewModelFactory;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.ui.GithubFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private GithubFragmentViewModel viewModel;

    @Inject GithubFragmentViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject
        RetrofitMode.getInstance()
                .retrofitSubcomponent()
                .retrofitModule(new RetrofitModule(this))
                .build()
                .inject(this);

        // Set up fragment with viewmodel
        GithubFragment githubFragment = new GithubFragment();
        viewModel = ViewModelProviders.of(this, factory).get(GithubFragmentViewModel.class);
        githubFragment.setGithubFragmentViewModel(viewModel);

        // Add fragment to activity
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, githubFragment);
        fragmentTransaction.commit();
    }
}
