package com.hca.hcatask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hca.hcatask.base.BaseViewModel
import com.hca.hcatask.domain.ApiHelper
import com.hca.hcatask.domain.NetworkStatus
import androidx.lifecycle.viewModelScope
import com.hca.hcatask.model.AnswerResponse
import com.hca.hcatask.utils.print
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class AnswerViewModel @Inject constructor(
        private val repository: ApiHelper
) : BaseViewModel() {

    private val answersLiveData: MutableLiveData<NetworkStatus<AnswerResponse>> = MutableLiveData()

    fun getAnswerLiveData(): LiveData<NetworkStatus<AnswerResponse>> = answersLiveData

    fun getAnswersData(question_id: Int) {

        viewModelScope.launch {
            try {
                repository.getAnswersData(question_id).let {
                    it.let {
                        "success error $it ".print()
                        answersLiveData.postValue(NetworkStatus.Loading(false))
                        answersLiveData.postValue(NetworkStatus.Success(it))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "catch error $e ".print()
                answersLiveData.postValue(NetworkStatus.Loading(false))
            }
        }
    }
}