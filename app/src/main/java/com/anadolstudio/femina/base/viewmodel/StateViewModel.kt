package com.anadolstudio.femina.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.anadolstudio.femina.base.livedata.onNextContent
import com.anadolstudio.femina.base.livedata.toImmutable

abstract class StateViewModel<Data> : BaseViewModel() {

    protected val _state = MutableLiveData<BaseViewState<Data>>()
    val state = _state.toImmutable()

    protected fun getContentOrNull(): Data? = (_state.value as? BaseViewState.Content<Data>)?.content

    protected fun updateContent(action: Data.() -> Data) {
        val content = getContentOrNull() ?: return
        _state.onNextContent(action.invoke(content))
    }
}
