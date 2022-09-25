package com.example.common_base.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<B: ViewDataBinding, I>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract fun getLayoutResId():Int
    private lateinit var mViewBinding:B
    val mItems = mutableListOf<I>()
    var mOnClickListener: View.OnClickListener? = null

    fun setItem(list: ArrayList<I>) {
        mItems.addAll(list)
    }

    // 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayoutResId(), parent, false)
        return BaseViewHolder(mViewBinding)
    }

    // View 내용 입력
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is BaseViewHolder) {
//            holder.binding.setVariable(BR.item, mItems[position])
//            if (mOnClickListener != null) {
//                holder.binding.setVariable(BR.clickListener, mOnClickListener)
//            }
//        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount() = mItems.size

    inner class BaseViewHolder(val binding:B):RecyclerView.ViewHolder(binding.root) {

    }
}
