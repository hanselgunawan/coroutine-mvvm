package com.hanseltritama.coroutinesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanseltritama.coroutinesmvvm.models.Owner
import com.hanseltritama.coroutinesmvvm.models.RecyclerList
import com.hanseltritama.coroutinesmvvm.network.RetrofitInstance
import com.hanseltritama.coroutinesmvvm.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()
    var userLiveData: MutableLiveData<Owner> = MutableLiveData()

    private val retrofitInstance =
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

    fun makeApiCall() {
        // Coroutines
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)
        }
    }

    fun getUserDataApi(loginName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitInstance.getUserDataApi(loginName)
            userLiveData.postValue(response)
        }
    }

}