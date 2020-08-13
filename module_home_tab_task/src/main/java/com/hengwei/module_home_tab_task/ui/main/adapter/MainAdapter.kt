package com.hengwei.module_home_tab_task.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.geen.commonlibary.utils.ToastUtil
import com.geen.commonlibary.widget.ExpandView
import com.hengwei.module_home_tab_task.R
import com.hengwei.module_home_tab_task.bean.MainInfo

/***
 * @author 86153
 * 主页adapter
 */
class MainAdapter(data: MutableList<MainInfo>) : BaseQuickAdapter<MainInfo, BaseViewHolder>(R.layout.tab_task_item_main, data) {
    override fun convert(baseViewHolder: BaseViewHolder, mainInfo: MainInfo) {
        baseViewHolder.setText(R.id.textTitle, mainInfo.title)
        val expandView = baseViewHolder.getView<ExpandView>(R.id.expandView)
        if (mainInfo.isShowExpandView) {
            expandView.visibility = View.VISIBLE
            baseViewHolder.getView<View>(R.id.imageArrow).isSelected = true
        } else {
            expandView.visibility = View.GONE
            expandView.collapse()
            baseViewHolder.getView<View>(R.id.imageArrow).isSelected = false
        }
        val rootExpandView = expandView.setExpandViewLayout(R.layout.tab_task_layout_expand)
        val recyclerView: RecyclerView = rootExpandView.findViewById(R.id.expandRecycleView)
        val adapter = ExpandAdapter(mainInfo.expandItemList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter1: BaseQuickAdapter<*, *>?, view: View?, position: Int -> ToastUtil.showTips("点击了$position") }
    }
}