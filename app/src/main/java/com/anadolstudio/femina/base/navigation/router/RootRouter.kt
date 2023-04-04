package com.anadolstudio.femina.base.navigation.router

import com.anadolstudio.femina.base.navigation.command.AddScreen
import com.anadolstudio.femina.base.navigation.command.BackAndReplace
import com.anadolstudio.femina.base.navigation.command.BackInclusive
import com.anadolstudio.femina.base.navigation.command.NewRootScreen
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class RootRouter : Router() {

    val resultSubscription: Subject<Pair<Int, Any?>> = PublishSubject.create<Pair<Int, Any?>>().toSerialized()

    override fun newRootScreen(screenKey: String?, data: Any?) {
        executeCommands(NewRootScreen(screenKey, data))
    }

    public override fun sendResult(resultCode: Int, result: Any?): Boolean {
        resultSubscription.onNext(resultCode to result)
        return super.sendResult(resultCode, result)
    }

    fun backToAndNavigateTo(backScreenKey: String, navigateToScreenKey: String, navigateToData: Any? = null) {
        executeCommands(BackTo(backScreenKey), Forward(navigateToScreenKey, navigateToData))
    }

    fun backAndReplace(replaceScreenKey: String, replaceTransactionData: Any? = null) {
        executeCommands(Back(), Replace(replaceScreenKey, replaceTransactionData))
    }

    fun backToAndReplace(backScreenKey: String, replaceScreenKey: String, replaceTransactionData: Any?) {
        executeCommands(BackAndReplace(backScreenKey, replaceScreenKey, replaceTransactionData))
    }

    fun backInclusive(screenKey: String) {
        executeCommands(BackInclusive(screenKey))
    }

    fun addScreen(screenKey: String, data: Any? = null) {
        executeCommands(AddScreen(screenKey, data))
    }
}