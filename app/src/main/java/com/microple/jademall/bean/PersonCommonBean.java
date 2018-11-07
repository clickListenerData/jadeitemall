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

public class PersonCommonBean {
    private int icon;
    private String name;
    private Class<? extends Activity> activity;

    public PersonCommonBean(int icon, String name, Class<? extends Activity> activity) {
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

    public static List<PersonCommonBean> createList(){
        List<PersonCommonBean> data = new ArrayList<>();
        data.add(new PersonCommonBean(R.drawable.ic_collect_n, "我的收藏", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.icon_attention_n,"我的关注", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.icon_footprint, "我的足迹", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.ic_appoint_n,"我的预约", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.ic_asset_n,"我的资产", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.ic_share_n1,"分享", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.ic_team_n,"我的团队", LiveDetailsActivity.class));
        data.add(new PersonCommonBean(R.drawable.ic_enter_n,"商家入驻申请", LiveDetailsActivity.class));
        return data;
    }
}
