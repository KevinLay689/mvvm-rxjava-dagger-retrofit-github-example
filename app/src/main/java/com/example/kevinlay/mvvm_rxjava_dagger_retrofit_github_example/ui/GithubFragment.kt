package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.R
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.GithubFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GithubFragment: Fragment() {

    private lateinit var searchButton: Button
    private lateinit var githubUsernameField: EditText

    private lateinit var viewModel: GithubFragmentViewModel

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_github, container, false)

        searchButton = view.findViewById(R.id.searchButton)
        githubUsernameField = view.findViewById(R.id.githubUsername)

        searchButton.setOnClickListener({ _ -> searchButtonClicked() })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposable.clear()
    }

    fun setGithubFragmentViewModel(viewModel: GithubFragmentViewModel) {
        this.viewModel = viewModel
    }

    private fun searchButtonClicked() {
        val tempDisposable: Disposable = viewModel.getGithubRepos(githubUsernameField.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { repos ->
                    for (repo in repos) {
                        Log.i(TAG, repo.name)
                    }
                },
                        { error ->
                            Log.i(TAG, error.toString())
                        }
                )
        disposable.add(tempDisposable)
    }

    companion object {
        const val TAG = "GithubFragment"
    }
}