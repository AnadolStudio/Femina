package com.anadolstudio.femina.base.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anadolstudio.femina.utils.viewmodel.BaseViewState

fun <T> MutableLiveData<T>.toImmutable() = this as LiveData<T>

fun <T> MutableLiveData<T>.onNext(t: T) {
    this.value = t
}

fun <T> MutableLiveData<BaseViewState<T>>.onNextContent(t: T) {
    this.value = BaseViewState.Content(t)
}
