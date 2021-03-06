package com.microple.jademall.bean;

import android.app.Activity;

import com.microple.jademall.R;
import com.microple.jademall.ui.live.activity.LiveDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: linfeng
 * date: 2018/8/8.
 * describe:个人中心列表
 */

public class PersonHelpBean {
    private int icon;
    private String name;
    private Class<? extends Activity> activity;

    public PersonHelpBean(int icon, String name, Class<? extends Activity> activity) {
        this.icon = icon;
        this.name = name;
        this.activity = activity;
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getActivity() {
        return activity;
    }

    public void setActivity(Class<? extends Activity> activity) {
        this.activity = activity;
    }

    public static List<PersonHelpBean> createList(){
        List<PersonHelpBean> data = new ArrayList<>();
        data.add(new PersonHelpBean(R.drawable.ic_issue_n, "常见问题", LiveDetailsActivity.class));
        data.add(new PersonHelpBean(R.drawable.ic_service_n,"联系客服", LiveDetailsActivity.class));
        data.add(new PersonHelpBean(R.drawable.ic_idea_n,"意见反馈", LiveDetailsActivity.class));
        data.add(new PersonHelpBean(R.drawable.ic_aboutus_n,"关于我们", LiveDetailsActivity.class));
        return data;
    }
}
