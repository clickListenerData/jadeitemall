package com.microple.jademall.ui.Personal.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.microple.jademall.R
import com.microple.jademall.bean.Pay
import com.microple.jademall.bean.PayTyle
import com.microple.jademall.bean.Pick
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.dialog.PayDialog
import com.microple.jademall.dialog.YunFeiDialog
import com.microple.jademall.ui.Personal.mvp.contract.PickGoodsContract
import com.microple.jademall.ui.Personal.mvp.presenter.PickGoodsPresenter
import com.microple.jademall.ui.home.activity.PaySucceefulActivity
import com.xx.anypay.XxAnyPay
import com.xx.anypay.XxAnyPayResultCallBack
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_pick_goods.*
import kotlinx.android.synthetic.main.item_title.*

/**
 * author: xiaoguagnfei
 * date: 2018/8/13
 * describe:提货中心
 */
class PickGoodsActivity : BaseMvpActivity<PickGoodsPresenter>(),PickGoodsContract.View {
    override fun getPayTyle(payTyle: PayTyle) {
        var dialog=PayDialog(this)
        for (i in 1..4){
            if(!payTyle.pay_type.contains("${i}")){
                dialog.setGone(i)
            }
        }

        dialog.show()
        dialog.setOnBtnClickListener {
            when(it){
                1->{
                    showLoadingDialog()
                    index=1
                    getPresenter().pay(Constants.getToken(),"1",pick?.user_address?.ua_id!!,intent.getStringExtra("ct_id"),"${shipping_pay}")
                }
                2->{
                    showLoadingDialog()
                    index=2
                    getPresenter().pay(Constants.getToken(),"2",pick?.user_address?.ua_id!!,intent.getStringExtra("ct_id"),"${shipping_pay}")

                }
            }
        }
    }

    var shipping_pay=2//1、到付，2、现付
    override fun pay(pay: Pay) {
        if (index==1||index==2){
            Log.i("singgg",pay.data.sign)
            XxAnyPay.intance
                    .openAnyPay(if (index == 1) XxAnyPay.XXPAY_WX else XxAnyPay.XXPAY_ALI,if (index == 1) Gson().toJson(pay.data) else pay.data.sign, object : XxAnyPayResultCallBack {
                        override fun onPayFiale(error: String) {
                            showToast(error)
                            dismissLoadingDialog()
                        }

                        override fun onPaySuccess() {
                            showToast("支付成功")
                            PaySucceefulActivity.startPaySucceefulActivity(mContext,pay.data.order_sn,1)
                            finish()
                        }
                    })

        }

    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): PickGoodsPresenter = PickGoodsPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_pick_goods

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_title.text="提货"
        XxAnyPay.intance.init(this)
        (application as App).addActivity(this)
        Log.i("ct_id",intent.getStringExtra("ct_id"))
        getPresenter().pick(Constants.getToken(),intent.getStringExtra("ct_id"))
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_back.setOnClickListener{
            finish()
        }
        rl_address.setOnClickListener{
            var intent=Intent(this,AddressActivity::class.java)
            intent.putExtra("flag",1)
            this.startActivityForResult(intent,3)
        }
        tv_pay.setOnClickListener{
            if (pick?.cut_status==0){
                showToast("未切石，不能提货")
                return@setOnClickListener
            }
            if (pick?.user_address?.ua_id==null){
                showToast("请添加地址")
            }else {
                var yunFeiDialog=YunFeiDialog(this)
                yunFeiDialog.show()
                yunFeiDialog.setOnBtnClickListener {
                    shipping_pay=it
                    getPresenter().getPayTyle(Constants.getToken(),"1")
                    yunFeiDialog.dismiss()


                }


            }

        }
    }
    //支付框
    var index=0
    var dialog:AlertDialog?=null
    var password:String?=null

    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }
    var pick: Pick?=null
    override fun pick(pick: Pick) {
        loading.visibility=View.GONE
        this.pick=pick
        if (pick.user_address.ua_id==null){
            add_address.visibility=View.VISIBLE
        }else{
            add_address.visibility=View.GONE
            tv_content.text=pick.user_address.consigner+"     "+pick.user_address.phone
            tv_address.text=pick.user_address.address
        }

        tv_number.text=pick.goods_sn
        tv_stye.text=pick.type
        tv_beizhu.text=pick.remark
        tv_fuwufei.text="￥${pick.service_fee}元"
        tv_yunfei.text="￥${pick.freight_price}元"
    }
    var name=""
    var phone=""
    var address=""
    var id=""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==3&&resultCode==3){
            name=data?.getStringExtra("name")!!
            phone=data?.getStringExtra("phone")
            address=data?.getStringExtra("address")
            id=data?.getStringExtra("id")
            tv_content.text=name+"       "+phone
            add_address.visibility=View.GONE
            tv_content.visibility=View.VISIBLE
            tv_address.visibility=View.VISIBLE
            tv_address.text=address
            pick?.user_address?.ua_id=id
        }

    }

    companion object {
        fun startPickGoodsActivity(context: Context,ct_id:String){
            val intent = Intent(context,PickGoodsActivity::class.java)
            intent.putExtra("ct_id",ct_id)
            context.startActivity(intent)
        }
    }

}
