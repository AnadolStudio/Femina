package com.anadolstudio.femina.base.navigation

import androidx.fragment.app.FragmentManager
import com.anadolstudio.femina.base.navigation.command.AddScreen
import com.anadolstudio.femina.base.navigation.command.BackAndReplace
import com.anadolstudio.femina.base.navigation.command.BackInclusive
import com.anadolstudio.femina.base.navigation.command.NewRootScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

abstract class FixSupportFragmentNavigator(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) : AndroidxFragmentNavigator(
        fragmentManager,
        containerId
) {

    override fun applyCommand(command: Command?) = when (command) {
        is NewRootScreen -> newRootScreen(command)
        is Replace -> customReplace(command)
        is BackAndReplace -> backAndReplace(command)
        is BackInclusive -> backInclusive(command.screenKey, immediate = true)
        is AddScreen -> addScreen(command)
        else -> super.applyCommand(command)
    }

    override fun showSystemMessage(message: String?) {
        //do nothing
    }

    private fun backInclusive(screenKey: String?, immediate: Boolean = false): Unit = with(fragmentManager) {
        if (screenKey == null) {
            smartPopBackStack(null, immediate)

            return@with
        }

        var hasScreen = false

        for (i in 0 until backStackEntryCount) {

            if (screenKey == getBackStackEntryAt(i).name) {
                smartPopBackStack(screenKey, immediate)
                hasScreen = true

                break
            }
        }

        if (!hasScreen) {
            backToUnexisting(screenKey)
        }
    }

    private fun FragmentManager.smartPopBackStack(screenKey: String?, immediate: Boolean) {
        when (immediate) {
            true -> popBackStackImmediate(screenKey, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            else -> popBackStack(screenKey, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun newRootScreen(command: NewRootScreen) = with(fragmentManager) {
        val fragment = createFragment(command.screenKey, command.transactionData)
        if (fragment == null) {
            unknownScreen(command)
            return@with
        }

        val fragmentTransaction = beginTransaction()

        setupFragmentTransactionAnimation(
                command,
                findFragmentById(containerId),
                fragment,
                fragmentTransaction)

        backInclusive(null)

        beginTransaction()
                .replace(containerId, fragment)
                .commit()

        executePendingTransactions()
    }

    private fun customReplace(command: Replace) = with(fragmentManager) {
        val fragment = createFragment(command.screenKey, command.transitionData)
        if (fragment == null) {
            unknownScreen(command)
            return@with
        }

        if (backStackEntryCount > 0) popBackStack()

        var fragmentTransaction = beginTransaction()

        setupFragmentTransactionAnimation(
                command,
                findFragmentById(containerId),
                fragment,
                fragmentTransaction
        )

        fragmentTransaction = fragmentTransaction.replace(containerId, fragment)
        if (backStackEntryCount > 0) fragmentTransaction = fragmentTransaction.addToBackStack(command.screenKey)
        fragmentTransaction.commit()

        executePendingTransactions()
    }

    private fun backAndReplace(command: BackAndReplace) = with(fragmentManager) {
        val fragment = createFragment(command.replaceScreenKey, command.replaceTransactionData)

        if (fragment == null) {
            unknownScreen(command)

            return@with
        }

        backInclusive(command.backScreenKey)

        val fragmentTransaction = beginTransaction()
        setupFragmentTransactionAnimation(
                command,
                findFragmentById(containerId),
                fragment,
                fragmentTransaction)

        fragmentTransaction
                .replace(containerId, fragment)
                .addToBackStack(command.replaceScreenKey)
                .commit()

        executePendingTransactions()
    }

    private fun addScreen(command: AddScreen): Unit = with(fragmentManager) {
        val fragment = createFragment(command.screenKey, command.data)
        beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(command.screenKey)
                .commit()

        executePendingTransactions()
    }

}