package com.hengwei.module_home_tab_task.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hengwei.module_home_tab_task.R
import com.hengwei.module_home_tab_task.bean.ExpandItem

/***
 * @author 86153
 * 扩展的子列表
 */
class ExpandAdapter(data: MutableList<ExpandItem>) : BaseQuickAdapter<ExpandItem, BaseViewHolder>(R.layout.tab_task_item_expand, data) {
    override fun convert(baseViewHolder: BaseViewHolder, expandItem: ExpandItem) {
        baseViewHolder.setText(R.id.textTitle, expandItem.expandString)
    }
}