package com.example.common_base.view.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common_base.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.base.BaseResources
import com.example.common_base.databinding.FragmentComponentsBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ComponentsFragment : BaseFragment<FragmentComponentsBinding>(R.layout.fragment_components) {

    override fun init() {
        binding.component = this
    }

    fun openAlertDialog(v: View) {
        CustomDialogFragment().apply {
            val fragment = DialogMessageFragment().apply {
                dialogMessage = BaseResources.getStringById(R.string.message1)
                dialogDetailMessage =
                    BaseResources.getStringById(R.string.message2)
//                dialogMessage = BaseResources.getStringById(R.string.message1)
//                dialogDetailMessage =
//                    BaseResources.getStringById(R.string.message2)
            }
            contentFragment = fragment
            title = "다이어로그"
            titleCenterPosition = true
            closeImg = true
            whiteBtnText = "취소"
            redBtnText = "확인"
            whiteBtnClick = View.OnClickListener {
                this.dismiss()
            }
            redBtnClick = View.OnClickListener {
                Snackbar.make(v, "확인~", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }.show(childFragmentManager, tag)
    }

    fun openSnackBar(v:View) {
        Snackbar.make(v, "openSnackBar~", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    fun moveNext(v: View) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

}