package com.anadolstudio.femina.base.activity

import androidx.appcompat.app.AppCompatActivity
import com.anadolstudio.femina.base.event.SingleErrorDialog
import com.anadolstudio.femina.base.event.SingleMessageDialog
import com.anadolstudio.femina.base.livedata.SingleError
import com.anadolstudio.femina.base.livedata.SingleEvent
import com.anadolstudio.femina.base.livedata.SingleMessage

abstract class CoreBaseSingleActivity : AppCompatActivity() {

    abstract fun defaultErrorMessage(): String

    abstract fun showError(event: SingleError)

    abstract fun showMessage(event: SingleMessage)

    abstract fun showMessageDialog(event: SingleMessageDialog)

    abstract fun showErrorDialog(event: SingleErrorDialog)

    abstract fun handleEvent(event: SingleEvent)
}
