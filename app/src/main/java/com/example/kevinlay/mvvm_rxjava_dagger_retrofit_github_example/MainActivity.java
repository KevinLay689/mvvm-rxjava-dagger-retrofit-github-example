package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger
        .DaggerAppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.DatabaseModule;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.RetrofitModule;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.AppDatabase;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.Repo;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.MainActivityViewModel;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.MainActivityViewModelFactory;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainActivityViewModel viewModel;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject MainActivityViewModelFactory factory;
    @Inject
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Repo> data = new ArrayList<>();
        data.add(new Repo("1", "app1", "someuser"));
        data.add(new Repo("2", "app2", "someuser"));

        DaggerAppComponent.builder()
                .databaseModule(new DatabaseModule(getApplication()))
                .retrofitModule(new RetrofitModule(this))
                .build()
                .inject(this);

        Completable completable = Completable.fromAction(() -> appDatabase.repoDao().insertRepos(data));

        disposable.add(
                completable
                        .subscribeOn(Schedulers.io())
                        .subscribe(() -> {

                        }));

        disposable.add(appDatabase.repoDao().getAllRepos().subscribe(repos -> Log.i(TAG, repos.toString())));

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
