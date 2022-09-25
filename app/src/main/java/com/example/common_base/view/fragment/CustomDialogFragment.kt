package com.example.common_base.view.fragment

import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.common_base.R
import com.example.common_base.base.BaseDialogFragment
import com.example.common_base.databinding.FragmentDialogLayoutBinding


class CustomDialogFragment : BaseDialogFragment<FragmentDialogLayoutBinding>(R.layout.fragment_dialog_layout) {
    var title = ""                                      // 다이어로그 제목
    var titleCenterPosition = false                     // 다이어로그 제목 위치 중앙으로 배치
    var closeImg = false                                // x(닫기) 이미지
    var whiteBtnText = ""                               // 버튼1 텍스트
    var whiteBtnClick : View.OnClickListener? = null    // 버튼1 클릭 이벤트
    var redBtnText = ""                                 // 버튼2 텍스트
    var redBtnClick : View.OnClickListener? = null      // 버튼2 클릭 이벤트
    var contentFragment: Fragment? = null               // 다이어로그 내용 Fragment

    override fun init() {
        binding.dialog = this
        if (titleCenterPosition) {
            val layoutParams = binding.dialogTitle.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            binding.dialogTitle.layoutParams = layoutParams
        }
        // 뒤로가기 및 백그라운드 클릭시 닫기 방지
//        isCancelable = false
        // 내용 view 결합
        contentFragment?.let { childFragmentManager.beginTransaction().replace(R.id.alertDialogParent, it).commit() }
    }

    // 다이어로그 닫기
    fun closeDialog(v:View) {
        this.dismiss()
    }

}