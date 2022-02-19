package com.company.nytimes.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import artifact.networking.model.*
import com.company.data.repo.DataLayerInterfaceImp
import com.company.data.repo.DomainInterfaceImp
import com.company.domain.model.Articles
import com.company.domain.usecase.GetNewsUseCase
import com.company.nytimes.common.base.BaseViewModel
import com.company.nytimes.common.utils.AppManager.errorMessage
import com.company.nytimes.common.utils.Load
import com.company.nytimes.common.utils.SomethingWentWrong
import com.company.nytimes.common.utils.StopLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel(app) {

    private val articles: MutableLiveData<ArrayList<Articles>> = MutableLiveData()
    val articlesLive: LiveData<ArrayList<Articles>> = articles
    var selectedArticle: Articles = Articles()

    init {
        getNews()
    }
    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            enqueueSignal(Load)
            try {
                when (val response = getNewsUseCase.invoke().map()) {
                    is Ok -> {
                        articles.postValue(response.data.results!!)
                        enqueueSignal(StopLoading)
                    }
                    is ClientError -> {
                        errorMessage = response.message.toString()
                        enqueueSignal(StopLoading, SomethingWentWrong.ErrorMessage)
                    }
                    is ServerError -> {
                        errorMessage = response.message.toString()
                        enqueueSignal(StopLoading, SomethingWentWrong.ErrorMessage)
                    }
                }
            } catch (t: Throwable) {
                errorMessage = "connection fail"
                enqueueSignal(StopLoading, SomethingWentWrong.ConnectionFailure)
            }
        }
    }


}