package com.gaaan.popularlibraries.mvpuser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gaaan.popularlibraries.App
import com.gaaan.popularlibraries.R
import com.gaaan.popularlibraries.data.GitHubUser
import com.gaaan.popularlibraries.databinding.ViewUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.view_user), UserView {

    private lateinit var viewBinding: ViewUserBinding

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(userLogin = userLogin).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ViewUserBinding.bind(view)
        viewBinding.userLogin.text = userLogin
    }

    override fun showUser(user: GitHubUser) {
        viewBinding.userProperties.visibility = View.VISIBLE
        Glide.with(viewBinding.userAvatar.context)
            .load(user.avatarUrl)
            .into(viewBinding.userAvatar)
        viewBinding.userLogin.text = user.login
        viewBinding.userType.text = user.type
        viewBinding.userId.text = user.id
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userId)
                }
            }
    }
}
