package com.anadolstudio.femina.base.bottom_sheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.anadolstudio.core.view.snackbar.NoSwipeBehavior
import com.anadolstudio.femina.R
import com.anadolstudio.femina.extension.decorate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseBottomDialogFragment(private val layoutId: Int) : BottomSheetDialogFragment() {

    abstract fun getDialogTag(): String

    protected open val isRounded = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isRounded) setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutId, container, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        bottomSheetDialog.setOnShowListener {
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            setDialogOptions(bottomSheet)
        }
        return bottomSheetDialog
    }

    fun show(fragmentManager: FragmentManager) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag(getDialogTag())
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        this.show(ft, getDialogTag())
    }

    protected fun setDialogOptions(
            bottomSheet: View?,
            viewOptionsInstaller: ((View) -> Unit)? = null
    ) {
        bottomSheet?.let { bottomSheetView ->
            viewOptionsInstaller?.invoke(bottomSheetView)
            BottomSheetBehavior.from(bottomSheetView).apply {
                skipCollapsed = true
                setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }
    }

    protected fun showSnackbar(
            message: String,
            @ColorRes backgroundColor: Int,
            @ColorRes textColor: Int,
            textAppearance: Int
    ) {
        Snackbar.make(requireDialog().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).apply {
            behavior = NoSwipeBehavior()
            decorate(backgroundColor, textColor, textAppearance)
        }.show()
    }
}
