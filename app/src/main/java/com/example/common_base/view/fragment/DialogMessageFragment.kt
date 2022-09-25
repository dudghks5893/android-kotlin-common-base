package com.example.common_base.view.fragment

import com.example.common_base.R
import com.example.common_base.base.BaseFragment
import com.example.common_base.databinding.FragmentDialogMessageBinding


class DialogMessageFragment : BaseFragment<FragmentDialogMessageBinding>(R.layout.fragment_dialog_message) {
    var dialogMessage = ""                                    // 다이어로그 메시지
    var dialogDetailMessage = ""                              // 다이어로그 상세 메시지
    var hasDialogIcon = true                                  // 다이어로그 이미지

    override fun init() {
        binding.dialogMsg = this
    }
}