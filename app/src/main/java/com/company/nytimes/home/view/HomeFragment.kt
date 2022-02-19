package com.company.nytimes.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.domain.model.Articles
import com.company.nytimes.R
import com.company.nytimes.common.base.BaseFragment
import com.company.nytimes.common.utils.*
import com.company.nytimes.databinding.FragmentHomeBinding
import com.company.nytimes.home.Adapter.ArticlesAdapter
import com.company.nytimes.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by activityViewModels()


    private val itemClickListener: (article:Articles) -> Unit =
        { selectedArticle ->
            vm.selectedArticle=selectedArticle
            NavigationHelper.navigate(findNavController()
                , androidx.appcompat.R.id.home
            ,R.id.detailsFragment)
        }

    private lateinit var articlesAdapter: ArticlesAdapter

    private val signalsHandler: (Signal) -> Unit = { signal ->
        when (signal) {
            is Load -> showProgressBar()
            is StopLoading -> hideProgressBar()
            is SomethingWentWrong.ErrorMessage -> makeToast(AppManager.errorMessage)
            is SomethingWentWrong.ConnectionFailure -> makeToast(AppManager.errorMessage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signalsReceiver.initializeSignalsReceiver(signalsHandler, vm)
        initView()
        subscribeData()

    }
    private fun initView() {

        articlesAdapter = ArticlesAdapter (clickCallBack = itemClickListener)

        binding.articlesRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = articlesAdapter
        }
    }



    private fun subscribeData() {
        vm.articlesLive.observe(viewLifecycleOwner) {
            articlesAdapter.setData(it)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}