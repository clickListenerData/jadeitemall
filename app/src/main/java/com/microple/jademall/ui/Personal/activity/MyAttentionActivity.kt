package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.microple.jademall.R
import com.microple.jademall.bean.Attention
import com.microple.jademall.ui.Personal.adapter.AttentionAdapter
import com.microple.jademall.ui.Personal.mvp.contract.MyAttentionContract
import com.microple.jademall.ui.Personal.mvp.presenter.MyAttentionPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_my_attention.*
import kotlinx.android.synthetic.main.item_title.*

class MyAttentionActivity : BaseMvpActivity<MyAttentionPresenter>(), MyAttentionContract.View  {
    override fun getAttention(attention: Attention) {
        adapter.setNewData(attention.follow)


    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): MyAttentionPresenter = MyAttentionPresenter()
    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_my_attention
    /**
     * 初始化数据状态
     */
    var adapter=AttentionAdapter(arrayListOf())
    override fun initData() {
        tv_title.text="我的关注"
        rc_attention.layoutManager=LinearLayoutManager(this)
        rc_attention.adapter=adapter
        getPresenter().getAttention()
        adapter.setOnItemClickListener { adapter, view, position ->
            MyAttentionDetailActivity.startMyAttentionActivity(this,Gson().toJson((adapter as AttentionAdapter).data[position].live_list))
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener { finish() }
    }

    companion object {
        fun startMyAttentionActivity(context: Context){
            val intent = Intent(context,MyAttentionActivity::class.java)
            context.startActivity(intent)
        }
    }

}
