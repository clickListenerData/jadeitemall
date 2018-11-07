package com.microple.jademall.ui.Personal.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.microple.jademall.R
import com.microple.jademall.bean.Assert
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.mvp.contract.MyAssetContract
import com.microple.jademall.ui.Personal.mvp.presenter.MyAssetPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_my_asset.*
import kotlinx.android.synthetic.main.item_title.*
/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:我的资产
 */
class MyAssetActivity : BaseMvpActivity<MyAssetPresenter>(),MyAssetContract.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): MyAssetPresenter = MyAssetPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_my_asset

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_title.text="我的资产"
        (application as App).addActivity(this)
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_detail.setOnClickListener{
            BillQueryActivity.startBillQueryActivity(this)
        }
        tv_zhuangzhang.setOnClickListener{
            IntergrationPushActivity.startIntergrationPushActivity(this)
        }
        tv_feicui.setOnClickListener{
            EmeraldsActivity.startEmeraldsActivity(this,1)
        }
        tv_jifen.setOnClickListener {
            EmeraldsActivity.startEmeraldsActivity(this,2)
        }
        iv_back.setOnClickListener{
            finish()
        }
    }

    override fun getMyAssert(assert: Assert) {
        loading.visibility= View.GONE
        tv_zichan.text=""+assert.assets.total_points
        tv_keyong.text=""+assert.assets.pay_points
        tv_dongjie.text=""+assert.assets.frozen_points
        tv_jinri.text=""+assert.assets.today_points
    }

    override fun onResume() {
        super.onResume()
        getPresenter().getMyAssert(Constants.getToken())

    }
    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }

    companion object {
        fun startMyAssetActivity(context: Context){
            val intent = Intent(context,MyAssetActivity::class.java)
            context.startActivity(intent)
        }
    }

}
