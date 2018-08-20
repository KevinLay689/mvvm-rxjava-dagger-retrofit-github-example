package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.R
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.Repo
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.GithubFragmentViewModel
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class GithubFragment: Fragment() {

    private lateinit var searchAndAdd: Button
    private lateinit var searchDbForUser: Button
    private lateinit var githubUsernameField: EditText
    private lateinit var results: ListView

    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var viewModel: GithubFragmentViewModel

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_github, container, false)

        searchAndAdd = view.findViewById(R.id.searchAndAddButton)
        searchDbForUser = view.findViewById(R.id.searchLocalDatabaseButton)
        githubUsernameField = view.findViewById(R.id.githubUsername)
        results = view.findViewById(R.id.results)

        searchAndAdd.setOnClickListener({ _ -> searchButtonClicked() })
        searchDbForUser.setOnClickListener({ _ -> searchDbButtonClicked(githubUsernameField.text.toString()) })

        return view
    }

    private fun searchDbButtonClicked(username: String) {
        val tempDisposable: Disposable = viewModel.getReposFromDatabase(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos ->
                    adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, repos)
                    results.adapter = adapter
                }

        disposable.add(tempDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposable.clear()
    }

    fun setGithubFragmentViewModel(viewModel: GithubFragmentViewModel) {
        this.viewModel = viewModel
    }

    private fun searchButtonClicked() {
        val tempDisposable: Disposable = viewModel
                .getGithubRepos(githubUsernameField.text.toString())
                .flatMapCompletable { githubRepos ->
                    val repoList = ArrayList<Repo>()

                    for (githubRepo: GithubRepo in githubRepos) {
                        val repo = Repo(owner = githubUsernameField.text.toString(), repoName = githubRepo.name)
                        repoList.add(repo)
                    }

                    viewModel.addUsersToDatabase(repoList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.i(TAG, "Added to db") },
                        { error -> Log.i(TAG, error.toString())
                })

        disposable.add(tempDisposable)
    }

    companion object {
        const val TAG = "GithubFragment"
    }
}
