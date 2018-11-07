package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.microple.jademall.R
import com.microple.jademall.bean.Attention
import com.microple.jademall.bean.LiveDetail
import com.microple.jademall.ui.Personal.adapter.AttentionDetailAdapter
import com.microple.jademall.ui.live.activity.LiveDetailsActivity
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import kotlinx.android.synthetic.main.activity_my_attention_detail.*
import kotlinx.android.synthetic.main.item_title.*

class MyAttentionDetailActivity : BaseMvpViewActivity() {
    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_my_attention_detail

    /**
     * 初始化数据状态
     */
    override fun initData() {
        initView()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }
    private fun initView() {
        tv_title.text="直播列表"
        var content=intent.getStringExtra("content")
       var list=Gson().fromJson<Array<Attention.FollowBean.LiveListBean>>(content,Array<Attention.FollowBean.LiveListBean>::class.java).toMutableList()
        rc_detail.layoutManager=LinearLayoutManager(this)
        var adapter=AttentionDetailAdapter(list)
        rc_detail.adapter=adapter
        iv_back.setOnClickListener { finish() }
        adapter.setOnItemClickListener { adapter, view, position ->
            LiveDetailsActivity.startLiveDetail(this,(adapter as AttentionDetailAdapter).data[position].live_id)
        }
    }

    companion object {
        fun startMyAttentionActivity(context: Context,content:String){
            val intent = Intent(context,MyAttentionDetailActivity::class.java)
            intent.putExtra("content",content)
            context.startActivity(intent)
        }
    }

}
