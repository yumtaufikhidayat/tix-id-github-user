package com.taufik.tixidgithubuser.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.tixidgithubuser.data.viewmodel.TixIdViewModel
import com.taufik.tixidgithubuser.databinding.FragmentMainBinding
import com.taufik.tixidgithubuser.ui.adapter.MainAdapter
import com.taufik.tixidgithubuser.ui.adapter.MainLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MainAdapter
    private val viewModel by viewModels<TixIdViewModel>()

    companion object {
        const val TAG = "MAIN_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {

        adapter = MainAdapter()

        binding.apply {
            rvAllUsers.layoutManager = LinearLayoutManager(requireActivity())
            rvAllUsers.setHasFixedSize(true)
            rvAllUsers.itemAnimator = null
            rvAllUsers.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MainLoadStateAdapter { adapter.retry() },
                footer = MainLoadStateAdapter { adapter.retry() }
            )
            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.data.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            } else {
                Log.d(TAG, "setData: $it")
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBarMain.isVisible = loadState.source.refresh is LoadState.Loading
                rvAllUsers.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && adapter.itemCount < 1
                ) {
                    rvAllUsers.isVisible = false
                    tvNoResultError.isVisible = true
                } else {
                    tvNoResultError.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}