package com.hengwei.module_main;

/***
 * @author 86153
 * 底部tab
 */
public class TabItem {

    private String tabText;
    private int  tabImage;

    public TabItem(String tabText, int tabImage) {
        this.tabText = tabText;
        this.tabImage = tabImage;
    }

    public String getTabText() {
        return tabText;
    }

    public void setTabText(String tabText) {
        this.tabText = tabText;
    }

    public int getTabImage() {
        return tabImage;
    }

    public void setTabImage(int tabImage) {
        this.tabImage = tabImage;
    }
}
