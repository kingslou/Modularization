package com.hengwei.module_home_tab_task.ui.main.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geen.commonlibary.widget.ExpandView;
import com.hengwei.module_home_tab_task.R;
import com.hengwei.module_home_tab_task.bean.MainInfo;

import java.util.List;

/***
 * @author 86153
 * 主页adapter
 */
public class MainAdapter extends BaseQuickAdapter<MainInfo, BaseViewHolder> {

    public MainAdapter(List<MainInfo> data) {
        super(R.layout.tab_task_item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MainInfo mainInfo) {
        baseViewHolder.setText(R.id.textTitle, mainInfo.getTitle());
        ExpandView expandView = baseViewHolder.getView(R.id.expandView);

        if (mainInfo.isShowExpandView()) {
            expandView.setVisibility(View.VISIBLE);
            baseViewHolder.getView(R.id.imageArrow).setSelected(true);
        } else {
            expandView.setVisibility(View.GONE);
            expandView.collapse();
            baseViewHolder.getView(R.id.imageArrow).setSelected(false);
        }

        View rootExpandView = expandView.setExpandViewLayout(R.layout.tab_task_layout_expand);
        RecyclerView recyclerView = rootExpandView.findViewById(R.id.expandRecycleView);
        ExpandAdapter adapter = new ExpandAdapter(mainInfo.getExpandItemList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
