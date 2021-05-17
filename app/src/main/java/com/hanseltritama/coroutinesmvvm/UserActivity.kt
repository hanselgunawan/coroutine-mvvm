package com.hanseltritama.coroutinesmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hanseltritama.coroutinesmvvm.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var loginName: String
    private lateinit var userName: String
    private lateinit var avatarUrl: String

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        loginName = intent.getStringExtra("login").toString()
        setupObserver()
        viewModel.getUserDataApi(loginName)
    }

    private fun setupObserver() {
        viewModel.userLiveData.observe(this, Observer { owner ->
            userName = owner.login
            avatarUrl = owner.avatar_url
            setupUI()
        })
    }

    private fun setupUI() {
        Picasso.get()
            .load(avatarUrl)
            .into(image_view_user)

        login_name_user.text = userName
    }
}