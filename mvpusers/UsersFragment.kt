package com.gaaan.popularlibraries.mvpusers


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gaaan.popularlibraries.App
import com.gaaan.popularlibraries.R
import com.gaaan.popularlibraries.data.GitHubUser
import com.gaaan.popularlibraries.databinding.ViewUsersBinding
import com.gaaan.popularlibraries.recycler.UsersAdapter

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.view_users), UsersView,
    UsersAdapter.OnUserClickListener {

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val usersAdapter = UsersAdapter(this)
    private lateinit var viewBinging: ViewUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinging = ViewUsersBinding.bind(view)
        viewBinging.usersRecycler.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUser>) {
        usersAdapter.submitList(users)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onUserPicked(user: GitHubUser) {
        presenter.goToNextScreen(user.login!!)
    }

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }
}

