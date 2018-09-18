package com.microple.jademall.ui.home.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.microple.jademall.R
import com.microple.jademall.dialog.BuytypeDialog
import com.microple.jademall.dialog.PayDialog
import com.microple.jademall.ui.home.adapter.ImOrderAdapter
import kotlinx.android.synthetic.main.activity_im_order.*
import kotlinx.android.synthetic.main.item_title.*
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.widget.TextView
import com.blankj.utilcode.util.ActivityUtils
import com.google.gson.Gson
import com.microple.jademall.bean.ImOrder
import com.microple.jademall.bean.Pay
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.activity.AddressActivity
import com.microple.jademall.ui.Personal.activity.PassswordActivity
import com.microple.jademall.ui.home.mvp.contract.ImOrderContract
import com.microple.jademall.ui.home.mvp.presenter.ImOrderPresenter
import com.microple.jademall.weight.PwdEditText
import com.weibiaogan.bangbang.common.md5Salt
import com.xx.anypay.XxAnyPay
import com.xx.anypay.XxAnyPayResultCallBack
import com.xx.baseuilibrary.mvp.BaseMvpActivity


/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:立即下单
 */
class ImOrderActivity : BaseMvpActivity<ImOrderPresenter>(),ImOrderContract.View {
    override fun pay(pay: Pay) {
        dismissLoadingDialog()
        if (indexs==1||indexs==2){
            XxAnyPay.intance
                    .openAnyPay(if (indexs == 1) XxAnyPay.XXPAY_WX else XxAnyPay.XXPAY_ALI,if (indexs == 1) Gson().toJson(pay.data) else pay.data.sign, object : XxAnyPayResultCallBack {
                        override fun onPayFiale(error: String) {
                            showToast(error)
                        }

                        override fun onPaySuccess() {
                            showToast("支付成功")
                            PaySucceefulActivity.startPaySucceefulActivity(mContext)
                            finish()
                        }
                    })

        }else if (indexs==3){
            finish()
            PaySucceefulActivity.startPaySucceefulActivity(mContext)
        }

    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): ImOrderPresenter = ImOrderPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_im_order

    /**
     * 初始化数据状态
     */
     var adapter=ImOrderAdapter(arrayListOf())
    var you_list= arrayListOf<String>()
    var live_list= arrayListOf<String>()
    var feicui_list= arrayListOf<String>()
    var you=""
    var live=""
    var feicui=""
    override fun initData() {
        tv_title.text="立即下单"
        (application as App).addActivity(this)
        XxAnyPay.intance.init(this)
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rv_myOrder.layoutManager= LinearLayoutManager(this)
        rv_myOrder.isNestedScrollingEnabled = false
        rv_myOrder.adapter=adapter
        adapter.setOnItemChildClickListener { adapter, view, position ->
            var goods=(adapter as ImOrderAdapter).data[position]
            if (view.id==R.id.ll_type){
                var dialog=BuytypeDialog(this)
                dialog.show()
                dialog.setOnBtnClickListener(object : BuytypeDialog.OnBtnClickListener {
                    override fun cancel(index: Int) {
                        when(index){
                            1->{
                                if (!you_list.contains(goods.goods_sn)){
                                    you_list.add(goods.goods_sn)
                                }
                                if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if (feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }
                            }
                            2->{
                                if (!live_list.contains(goods.goods_sn)){
                                    live_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }
                            }
                            3->{
                                if (!feicui_list.contains(goods.goods_sn)){
                                    feicui_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }
                            }
                         }
                        if (you_list.size!=0){
                            tv_youji.visibility=View.VISIBLE
                            for (i in 0..you_list.size-1){
                                if (i==0)
                                    you= you_list[i]
                                else
                                    you=you+"/"+you_list[i]
                            }
                            tv_youji.text="邮寄:                "+you
                        }else{
                            tv_youji.visibility=View.GONE
                        }
                        if(live_list.size!=0){
                                tv_zhibo.visibility=View.VISIBLE
                                for (i in 0..live_list.size-1){
                                    if (i==0)
                                        live= live_list[i]
                                    else
                                        live=live+"/"+live_list[i]
                                }
                                tv_zhibo.text="预约切石直播:"+live
                        }else{
                            tv_zhibo.visibility=View.GONE
                        }
                        if (feicui_list.size!=0){
                            tv_cun.visibility=View.VISIBLE
                            for (i in 0..feicui_list.size-1){
                                if (i==0)
                                    feicui= feicui_list[i]
                                else
                                    feicui=feicui+"/"+feicui_list[i]
                            }
                            tv_cun.text="存入翡翠柜:    "+feicui
                        }else{
                            tv_cun.visibility=View.GONE
                        }
                        dialog.dismiss()
                    }

                })
            }
        }
    }

    /**
     * 初始化事件
     */
    var indexs=0
    override fun initEvent() {
        tv_submint.setOnClickListener{
            var dialog=PayDialog(this)
            if (you_list.size+live_list.size+feicui_list.size!=adapter.data.size){
                showToast("请选择购买方式")
            }else if (order?.order!!.user_address==null){
                showToast("请添加地址")
            }else{
                dialog.show()
                dialog.setOnBtnClickListener(object : PayDialog.OnBtnClickListener {
                    override fun cancel(index: Int) {
                        dialog.dismiss()

                        when(index){
                            1->{
                                indexs=1
                                showLoadingDialog()
                                getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"1","")
                            }
                            2->{
                                indexs=2
                                showLoadingDialog()
                                getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"2","")

                            }
                            3->{
                                indexs=3
                                showDialog()

                            }
                        }
                    }

                })

            }

        }
        var index=1
        tv_buy.setOnClickListener{
            adapter.upDateAdapter(index)
            if (index==1){
                index=0
            }else{
                index=1
            }
        }
        iv_back.setOnClickListener{
            finish()
        }
        rl_address.setOnClickListener {
            AddressActivity.startAddressActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("sb_id",intent.getStringExtra("sb_id"))
        getPresenter().imOrder(Constants.getToken(),intent.getStringExtra("sb_id"),intent.getStringExtra("goods_id"))
    }
    var order:ImOrder?=null
    override fun imOrder(imOrder: ImOrder) {
        loading.visibility=View.GONE
        order=imOrder
        adapter.setNewData(imOrder.order.goods_info)
        tv_wuliu.text="物流费用:  ￥"+imOrder.order.shipping_fee
        tv_content.text=imOrder.order.user_address.consigner+"       "+imOrder.order.user_address.phone
        if (imOrder.order.user_address==null){
            add_address.visibility=View.VISIBLE
        }else{
            add_address.visibility=View.GONE
            tv_address.text=imOrder.order.user_address.address
        }
        tv_price.text="购物袋合计      ￥"+imOrder.order.total_fee
    }
    var password:String?=null
    var dialog:AlertDialog?=null
    //积分支付
    fun showDialog() {
        var view = View.inflate(mContext, R.layout.view_input_pay_psw_dialog, null)
        var psw_view=view.findViewById<PwdEditText>(R.id.psw_view)
        var tv_forget_pwd=view.findViewById<TextView>(R.id.tv_forget_pwd)
        var bt_quxiao=view.findViewById<TextView>(R.id.bt_quxiao)
        var bt_sure=view.findViewById<TextView>(R.id.bt_sure)
        bt_quxiao.setOnClickListener{
            dialog!!.dismiss()
        }
        bt_sure.setOnClickListener{
            if (password!=null&&password?.length==6){
                showLoadingDialog()
                dialog!!.dismiss()
                getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"3",password!!.md5Salt())
            }

        }
        tv_forget_pwd.setOnClickListener{

            PassswordActivity.startPassswordActivity(this,2)
        }
        psw_view.setOnInputFinishListener{
            password=it
        }
        dialog = AlertDialog.Builder(mContext).create()
        dialog!!.setView(view)
        dialog!!.show()
    }

    companion object {
        fun startImOrderActivity(context: Context,sb_id:String,goods_id:String){
            val intent = Intent(context, ImOrderActivity::class.java)
            intent.putExtra("sb_id",sb_id)
            intent.putExtra("goods_id",goods_id)
            context.startActivity(intent)
        }
    }

}
