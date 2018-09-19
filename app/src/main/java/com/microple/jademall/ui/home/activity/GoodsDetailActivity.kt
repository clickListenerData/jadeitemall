package com.microple.jademall.ui.home.activity

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.microple.jademall.R
import com.microple.jademall.bean.GoodsDetail
import com.microple.jademall.dialog.TextDialog
import com.microple.jademall.ui.home.mvp.contract.GoodsDetailContract
import com.microple.jademall.ui.home.mvp.presenter.GoodsDetailPresenter
import com.microple.jademall.uitls.loadImag
import com.microple.jademall.weight.ZoomScrollView
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_goods.*
import android.view.WindowManager
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.activity.LoginActivity
import com.microple.jademall.ui.Personal.adapter.ImageDetailAdapter


/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:商品详情
 */
class GoodsDetailActivity : BaseMvpActivity<GoodsDetailPresenter>(),GoodsDetailContract.View {
    override fun addShoping() {
        dismissLoadingDialog()
        showToast("加入购物袋成功")
        iv_car.setImageResource(R.drawable.bnt_bag_fill_s)
        iv_car.isEnabled=false
    }

    override fun collection(msg: String) {
        dismissLoadingDialog()
        if (msg.equals("收藏成功")){
            iv_collection.setImageResource(R.drawable.btn_favor_selected)
        }else{
            iv_collection.setImageResource(R.drawable.btn_favor_normal)
        }
        showToast(msg)

    }

    var goodsDetail: GoodsDetail?=null
    override fun getLabel(name:String,label_desc: String) {
        dismissLoadingDialog()
        var dialog=TextDialog(this,label_desc)
        dialog.setTitle(name,true)
        dialog.show()
        dialog.setOnBtnClickListener {
            dialog.dismiss()
        }
    }

    override fun getDetail(goodsDetail: GoodsDetail) {
        this.goodsDetail=goodsDetail
        initView(goodsDetail)

    }



    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): GoodsDetailPresenter = GoodsDetailPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_goods

    /**
     * 初始化数据状态
     */
    override fun initData() {
        (application as App).addActivity(this)
        var goods_sn=intent.getStringExtra("goods_sn")
        if (Constants.isLogin()){
            getPresenter().getDetail(Constants.getToken(),goods_sn)
        }else{
            getPresenter().getDetail("",goods_sn)
        }
        hideStatusBar()
    }

    //只透明状态栏
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_sure.setOnClickListener{
            if (Constants.isLogin())
            ImOrderActivity.startImOrderActivity(this,"",""+goodsDetails?.goods_info?.goods_id)
            else
                LoginActivity.startLoginActivity(this)
        }
        tv_other.setOnClickListener{
            showChangeSexDialogOne(data!!)
        }

        iv_img.setOnClickListener {
            if (goodsDetail!=null)
            ImageDetailActivity.startImageDetailActivity(this,""+goodsDetail?.goods_info?.goods_id!!)
        }
        tv_close.setOnClickListener {
            finish()
        }
        iv_collection.setOnClickListener{
            if (Constants.isLogin()){
                showLoadingDialog()
                getPresenter().collection(Constants.getToken(),""+goodsDetail?.goods_info?.goods_id)
            }else{
                showToast("请先登录")
            }
        }
        iv_car.setOnClickListener{
            if (Constants.isLogin()){
                showLoadingDialog()
                getPresenter().addShoping(Constants.getToken(),""+goodsDetail?.goods_info?.goods_id)
            }else{
                showToast("请先登录")
            }
        }
        zoomScrollView.setMoveViewListener {
                if (it==150){
                    finish()
                    overridePendingTransition(0,R.anim.translate_out)
                }

            }

    }

    companion object {
        fun startGoodsDetailActivity(context: Context,goods_sn:String){
            val intent = Intent(context, GoodsDetailActivity::class.java)
            intent.putExtra("goods_sn",goods_sn)
            context.startActivity(intent)
        }
    }
    var data:List<GoodsDetail.OtherSnBean>?=null
    var goodsDetails:GoodsDetail?=null
    private fun initView(goodsDetail: GoodsDetail) {
        loading.visibility=View.GONE
        goodsDetails=goodsDetail
        tv_title.text=goodsDetail.goods_info.goods_name
        tv_tt_number.text=goodsDetail.goods_info.goods_sn
        iv_zoom.loadImag(goodsDetail.goods_info.goods_img)
        tv_type.text="类型："+(if (goodsDetail.goods_info.type==0) "原石" else "成品")
        tv_number.text="产品编号："+goodsDetail.goods_info.goods_sn
        tv_chicun.text="尺寸：高"+goodsDetail.goods_info.height+"mm,宽"+goodsDetail.goods_info.width+"mm,厚"+goodsDetail.goods_info.thickness+"mm"
        tv_color.text="颜色："+goodsDetail.goods_info.color
        tv_touming.text="透明度："+goodsDetail.goods_info.transparency
        tv_changdi.text="产地："+goodsDetail.goods_info.origin_place
        tv_price.text="RMB    "+goodsDetail.goods_info.goods_price
        if (goodsDetail.goods_info.is_collect==1){
            iv_collection.setImageResource(R.drawable.btn_favor_selected)
        }
        data=goodsDetail.other_sn
        if (goodsDetail.goods_info.labels.size!=0) {
            ll_label.removeAllViews()
            for (i in 0..goodsDetail.goods_info.labels.size - 1) {
                if (goodsDetail.goods_info.labels[i] != null){
                var textView = TextView(this)
                var param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                param.leftMargin = 16
                textView.text = goodsDetail.goods_info.labels[i].label_name
                textView.setTextColor(resources.getColor(R.color.white_default))
                textView.textSize = 12f
                textView.layoutParams = param
                textView.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_jifen))
                textView.setOnClickListener {
                    showLoadingDialog()
                    getPresenter().getLabel(goodsDetail.goods_info.labels[i].label_name, "" + goodsDetail.goods_info.labels[i].label_id)
                }
                ll_label.addView(textView)
            }
        }
        }
        var imgs=goodsDetail.goods_info.goods_content.split(",")
        var datas=ArrayList<String>()
        if (imgs.size!=0)
        for ( i in 0..imgs.size-1){
            datas?.add(imgs[i])
        }
        var adapter= ImageDetailAdapter(datas as List<String>)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyclerView.isNestedScrollingEnabled=false
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter
    }

    private fun showChangeSexDialogOne(data:List<GoodsDetail.OtherSnBean>) {
        val items=Array<String>(data.size,{"12233"})
        for (s in 0 ..(data.size-1)){
            items.set(s,data.get(s).goods_sn)
        }

        val dialog = ActionSheetDialog(this, items, null).isTitleShow(false)
        dialog.titleTextColor(resources.getColor(R.color.black_333333))
        dialog.title("选择其他编号")
        dialog.isTitleShow(true)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
            override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tv_tt_number.text=data[position].goods_sn
                tv_number.text=data[position].goods_sn
                if (Constants.isLogin()){
                    getPresenter().getDetail(Constants.getToken(),data[position].goods_sn)
                }else{
                    getPresenter().getDetail("",data[position].goods_sn)
                }
                dialog.dismiss()
            }
        })
        dialog.show()
    }

}
