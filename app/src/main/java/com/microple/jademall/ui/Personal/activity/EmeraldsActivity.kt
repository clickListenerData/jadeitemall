package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.microple.jademall.R
import com.microple.jademall.bean.Emeralds
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.adapter.EmealdsAdapter
import com.microple.jademall.ui.Personal.mvp.contract.EmeraldsContract
import com.microple.jademall.ui.Personal.mvp.presenter.EmeraldsPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.activity_emeralds.*
import kotlinx.android.synthetic.main.item_title.*

/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:翡翠柜
 */
class EmeraldsActivity : BaseMvpActivity<EmeraldsPresenter>(),EmeraldsContract.View {
    var index=1//1代表翡翠柜，2代表积分柜
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): EmeraldsPresenter = EmeraldsPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_emeralds

    /**
     * 初始化数据状态
     */
    var adapter= EmealdsAdapter(arrayListOf())
    override fun initData() {
        index=intent.getIntExtra("index",index)
        if (index==1){
            tv_title.text="翡翠柜"
        }else{
            tv_title.text="积分柜"
        }
        (application as App).addActivity(this)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyclerView.layoutManager= GridLayoutManager(this,2)
        recyclerView.adapter=adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            EmeraldsDetailActivity.startOrderDetailActivity(this,""+(adapter as EmealdsAdapter).data[position].ct_id,(adapter as EmealdsAdapter).data[position].incr_id,index)
        }

    }

    override fun onResume() {
        super.onResume()
        loading.visibility=View.VISIBLE
        if (index==1)
        getPresenter().getEmeralds(Constants.getToken())
        else
            getPresenter().getjifen(Constants.getToken())
    }
    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener{
            finish()
        }
    }

    override fun getEmeralds(emeralds: Emeralds) {
        loading.visibility= View.GONE
        tv_all.text=""+emeralds.total_price
        tv_number.text=""+emeralds.goods_num
        adapter.setNewData(emeralds.goods_info)

    }

    companion object {
        fun startEmeraldsActivity(context: Context,index:Int){
            val intent = Intent(context,EmeraldsActivity::class.java)
            intent.putExtra("index",index)
            context.startActivity(intent)
        }
    }

}
