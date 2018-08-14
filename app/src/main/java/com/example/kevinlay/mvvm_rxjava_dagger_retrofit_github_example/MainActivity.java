package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.DaggerAppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubApi;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject Retrofit retrofit;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAppComponent.builder().build().inject(this);

        disposable.add(
                retrofit.create(GithubApi.class)
                        .getRepos("kevinlay689")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repos -> {
                            for (GithubRepo repo: repos) {
                                Log.i(TAG, repo.getName());
                            }
                        }));
    }
}
