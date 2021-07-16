package com.mahima.animestreamingapp.ui.mypet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MypetViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is mypet Fragment"
    }
    val text: LiveData<String> = _text
}