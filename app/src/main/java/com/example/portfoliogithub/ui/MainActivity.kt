package com.example.portfoliogithub.ui

import android.content.Intent
import android.net.Uri
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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by inject<RepoListAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter

        observeChangesUser()
        observeChangesRepo()
        setTapTarget()
        viewModel.getRepoList("maria-serafim-dev")

    }

    private fun setTapTarget() {
        TapTargetView.showFor(this,
            TapTarget.forToolbarMenuItem(
                binding.toolbar, R.id.action_search,
                getString(R.string.txt_title_tap_target),
                getString(R.string.txt_description_tap_target)
            ))
    }

    private fun observeChangesRepo() {
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
                    adapter.submitList(it.list)
                }
            }
        }
    }

    private fun observeChangesUser() {
        viewModel.user.observe(this){
            when (it) {
                MainViewModel.StateUser.Loading -> dialog.show()
                is MainViewModel.StateUser.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.StateUser.Success -> {
                    dialog.dismiss()
                    binding.tvNameOwner.text = it.user.name
                    binding.tvUserNameOwner.text = it.user.login
                    Glide.with(this).load(it.user.avatarURL).circleCrop().into(binding.ivOwner)
                    binding.btn.setOnClickListener{ _ ->
                        clickRepository(it.user.htmlURL)
                    }
                }
            }
        }
    }


    private fun clickRepository(htmlURL: String){
        val uri = Uri.parse(htmlURL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.main_menu, menu)
       val searchView = menu.findItem(R.id.action_search).actionView as SearchView
       searchView.setOnQueryTextListener(this)
       searchView.queryHint = getString(R.string.text_search_descrption)
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