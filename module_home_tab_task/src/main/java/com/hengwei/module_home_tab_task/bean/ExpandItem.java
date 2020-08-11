package com.hengwei.module_home_tab_task.bean;

import java.io.Serializable;

public class ExpandItem implements Serializable {

    private String expandString;

    public ExpandItem(String expandString) {
        this.expandString = expandString;
    }

    public void setExpandString(String expandString) {
        this.expandString = expandString;
    }

    public String getExpandString() {
        return expandString;
    }
}
