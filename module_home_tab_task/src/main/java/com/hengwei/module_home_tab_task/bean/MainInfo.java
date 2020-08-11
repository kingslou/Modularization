package com.hengwei.module_home_tab_task.bean;
import java.io.Serializable;
import java.util.List;

/***
 * @author 86153
 * 主页
 */
public class MainInfo implements Serializable {

    private String title;

    private boolean showExpandView;

    private List<ExpandItem> expandItemList;

    public MainInfo(String title,List<ExpandItem> expandItemList) {
        this.title = title;
        this.expandItemList = expandItemList;
    }

    public void setExpandItemList(List<ExpandItem> expandItemList) {
        this.expandItemList = expandItemList;
    }

    public List<ExpandItem> getExpandItemList() {
        return expandItemList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setShowExpandView(boolean showExpandView) {
        this.showExpandView = showExpandView;
    }

    public boolean isShowExpandView() {
        return showExpandView;
    }
}
