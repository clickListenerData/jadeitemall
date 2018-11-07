package com.microple.jademall.ui.live.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.microple.jademall.R
import com.microple.jademall.bean.LiveDetail
import com.microple.jademall.common.App
import com.microple.jademall.common.Constant
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.activity.LoginActivity
import com.microple.jademall.ui.live.mvp.contract.LiveDetailContract
import com.microple.jademall.ui.live.mvp.presenter.LiveDetailPresenter
import com.microple.jademall.uitls.loadImag
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import kotlinx.android.synthetic.main.activity_live_details.*

/**
 * author: linfeng
 * date: 2018/8/6.
 * describe:直播详情
 */
class LiveDetailsActivity : BaseMvpActivity<LiveDetailPresenter>(),LiveDetailContract.View {
    override fun liveYuyue(msg: String) {
        dismissLoadingDialog()
        showToast(msg)
    }
    var liveDetail: LiveDetail?=null
    override fun getDetail(liveDetail: LiveDetail) {
        loading.visibility=View.GONE
        this.liveDetail=liveDetail
        tv_liveMessage.text = liveDetail.detail.desc
        tv_liveName.text = liveDetail.detail.live_title
        tv_liveTime.text = liveDetail.detail.live_time
        iv_live.loadImag(liveDetail.detail.cover_img)
    }
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): LiveDetailPresenter = LiveDetailPresenter()

    /**
     * 初始化数据状态
     */
    override fun initData() {
        title = "直播详情"
        (application as App).addActivity(this)
        if (Constants.isLogin()){
            getPresenter().getDetail(Constants.getToken(),intent.getStringExtra("live_id"))
        }else{
            getPresenter().getDetail("",intent.getStringExtra( "live_id"))
        }
        Glide.with(this).load(Constant.item)
                        .placeholder(R.drawable.ic_img_default)
                        .error(R.drawable.ic_img_default)
                .into(iv_live)
        iv_player.setOnClickListener{
                LivePlayerActivity.startLivePlayerActivity(this,intent.getStringExtra("live_id"),liveDetail?.detail!!.play_url[0]!!,liveDetail?.detail!!.group_id,liveDetail?.detail?.live_title!!,1,liveDetail!!.detail.cover_img,""+liveDetail?.detail?.supplier_id)
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        bt_make.setOnClickListener {
            showLoadingDialog()
            if (Constants.isLogin()){
                showLoadingDialog()
                getPresenter().liveYuyue(Constants.getToken(),intent.getStringExtra("live_id"))
            }else{
                LoginActivity.startLoginActivity(this)
            }
        }
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_live_details
    companion object {
        fun startLiveDetail(context: Context, live_id:String){
            val intent = Intent(context, LiveDetailsActivity::class.java)
            intent.putExtra("live_id",live_id)
            context.startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }

}