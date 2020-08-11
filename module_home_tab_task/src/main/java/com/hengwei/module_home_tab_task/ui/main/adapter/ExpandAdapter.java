package com.hengwei.module_home_tab_task.ui.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengwei.module_home_tab_task.R;
import com.hengwei.module_home_tab_task.bean.ExpandItem;

import java.util.List;

/***
 * @author 86153
 * 扩展的子列表
 */
public class ExpandAdapter extends BaseQuickAdapter<ExpandItem, BaseViewHolder> {

    public ExpandAdapter(List<ExpandItem> data) {
        super(R.layout.tab_task_item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ExpandItem expandItem) {
        baseViewHolder.setText(R.id.textTitle,expandItem.getExpandString());
    }
}
