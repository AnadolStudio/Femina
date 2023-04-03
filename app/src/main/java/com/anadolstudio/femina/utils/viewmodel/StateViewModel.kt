package com.anadolstudio.femina.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anadolstudio.femina.base.livedata.SingleEvent
import com.anadolstudio.femina.base.livedata.SingleLiveEvent
import com.anadolstudio.femina.base.livedata.onNextContent
import com.anadolstudio.femina.base.livedata.toImmutable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class StateViewModel<Data> : BaseViewModel() {

    protected val _state = MutableLiveData<BaseViewState<Data>>()
    val state = _state.toImmutable()

    protected fun getContentOrNull(): Data? = (_state.value as? BaseViewState.Content<Data>)?.content

    protected fun updateContent(action: Data.() -> Data) {
        val content = getContentOrNull() ?: return
        _state.onNextContent(action.invoke(content))
    }
}
