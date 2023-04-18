package com.anadolstudio.femina.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import com.anadolstudio.femina.base.livedata.toImmutable

abstract class SimpleViewModel<Data> : BaseViewModel() {

    protected val _state = MutableLiveData<Data>()
    val state = _state.toImmutable()

}
