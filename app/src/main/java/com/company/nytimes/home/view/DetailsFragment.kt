package com.company.nytimes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.company.nytimes.databinding.FragmentDetailsBinding
import com.company.nytimes.home.viewmodel.HomeViewModel


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val vm: HomeViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.progressBar.visibility=View.VISIBLE

        // Enable Javascript
        val webSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true
        vm.selectedArticle.articleUrl?.let { binding.webview.loadUrl(it) }

        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                binding.progressBar.visibility=View.GONE

            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}