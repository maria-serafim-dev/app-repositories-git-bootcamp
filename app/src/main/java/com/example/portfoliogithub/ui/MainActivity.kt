package com.example.portfoliogithub.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.example.portfoliogithub.R
import com.example.portfoliogithub.core.createDialog
import com.example.portfoliogithub.core.createProgressDialog
import com.example.portfoliogithub.core.hideSoftKeyboard
import com.example.portfoliogithub.databinding.ActivityMainBinding
import com.example.portfoliogithub.presentation.MainViewModel
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter

        observeChanges()
        setTapTarget()

    }

    private fun setTapTarget() {
        TapTargetView.showFor(this,
            TapTarget.forToolbarMenuItem(
                binding.toolbar, R.id.action_search,
                getString(R.string.txt_title_tap_target),
                getString(R.string.txt_description_tap_target)
            ))
    }

    private fun observeChanges() {
        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    binding.tvNameOwner.text = it.list[0].owner.login
                    Glide.with(this).load(it.list[0].owner.avatarURL).circleCrop().into(binding.ivOwner)
                    adapter.submitList(it.list)
                }
            }
        }
    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.main_menu, menu)
       val searchView = menu.findItem(R.id.action_search).actionView as SearchView
       searchView.setOnQueryTextListener(this)
       searchView.queryHint = "Digite um username"
           return super.onCreateOptionsMenu(menu)
   }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    companion object {
        private const val TAG = "TAG"
    }
}