package com.nfscene.fetchnftdemo.fetchnft

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class FetchNftActivityViewModel(application: Application): AndroidViewModel(application) {
    private val response = MutableLiveData<String>()
    private val client = OkHttpClient()
    private val scope = CoroutineScope(Dispatchers.Main)

    companion object {
        val API_KEY = "REPLACE_WITH_YOUR_ALCHEMY_KEY"
    }

    fun getResponse(): LiveData<String> {
        return response
    }

    init {
        response.value = "Send a request first"
    }

    fun sendRequest(ethAddress: String) {
        val request = Request.Builder()
            .url("https://eth-mainnet.g.alchemy.com/v2/$API_KEY/getNFTs/?owner=${ethAddress}")
            .build()
        scope.launch {
            response.value = withContext(Dispatchers.Default) {
                client.newCall(request).execute()
            }.body?.string()
        }
    }
}