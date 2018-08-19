package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.AppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.DaggerAppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.RetrofitModule;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.RetrofitSubcomponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.MainActivityViewModel;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.MainActivityViewModelFactory;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainActivityViewModel viewModel;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject MainActivityViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerAppComponent.builder()
                .build()
                .retrofitComponent()
                .requestRetrofitModule(new RetrofitModule(this))
                .build()
                .inject(this);

        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable.add(
                viewModel.getGithubRepos("kevinlay689")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repos -> {
                            for (GithubRepo repo: repos) {
                                Log.i(TAG, repo.getName());
                            }
                        }));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
