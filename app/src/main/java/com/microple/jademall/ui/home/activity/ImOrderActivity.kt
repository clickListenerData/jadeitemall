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
import com.microple.jademall.bean.IsSettingPayPW
import com.microple.jademall.bean.OrderInfo
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
    var orderInfo:OrderInfo?=null
    override fun getOrderInfo(orderInfo: OrderInfo) {
        this.orderInfo=orderInfo
        tv_wuliu.text="物流费用:  ￥"+orderInfo.shipping_fee
        tv_price.text="合计      ￥"+orderInfo.total_fee
    }

    var isSetting=0
    override fun isSetting(isSettingPayPW: IsSettingPayPW) {
        isSetting=isSettingPayPW.set_password
    }

    override fun pay(pay: Pay) {
        dismissLoadingDialog()
        if (indexs==1||indexs==2){
            Log.i("singgg",pay.data.sign)
            XxAnyPay.intance
                    .openAnyPay(if (indexs == 1) XxAnyPay.XXPAY_WX else XxAnyPay.XXPAY_ALI,if (indexs == 1) Gson().toJson(pay.data) else pay.data.sign, object : XxAnyPayResultCallBack {
                        override fun onPayFiale(error: String) {
                            showToast(error)
                        }

                        override fun onPaySuccess() {
//                            showToast("支付成功")
                            PaySucceefulActivity.startPaySucceefulActivity(mContext,pay.data.order_sn,1)
                            finish()
                        }
                    })

        }else if (indexs==3){
            PaySucceefulActivity.startPaySucceefulActivity(mContext,pay.data.order_sn,1)
            finish()
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
    var youdao_list= arrayListOf<String>()
    var live_list= arrayListOf<String>()
    var feicui_list= arrayListOf<String>()
    var oneYear_list= arrayListOf<String>()
    var twoYear_list= arrayListOf<String>()
    var fiveYear_llist= arrayListOf<String>()
    var you=""
    var live=""
    var feicui=""
    var oneYear=""
    var twoYear=""
    var fiveYear=""
    var youdao=""


    override fun onDestroy() {
        super.onDestroy()
        (application as App).deleteActivity(this)
    }
    override fun initData() {
        tv_title.text="立即下单"
        getPresenter().imOrder(Constants.getToken(),intent.getStringExtra("sb_id"),intent.getStringExtra("goods_id"))
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
                if (goods.type==2){
                    dialog.setvisb(2)
                }else{
                    dialog.setvisb(1)
                }
                dialog.show()
                dialog.setOnBtnClickListener(object : BuytypeDialog.OnBtnClickListener {
                    override fun cancel(index: Int) {
                        when(index){
                            1->{//邮寄
                                if (!you_list.contains(goods.goods_sn)){
                                    you_list.add(goods.goods_sn)
                                }
                                if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if (feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                } else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if(twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                }else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                }
                                else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }
                            }
                            2->{//直播
                                if (!live_list.contains(goods.goods_sn)){
                                    live_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if(twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                }else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                } else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }
                            }
                            3->{//存入翡翠柜
                                if (!feicui_list.contains(goods.goods_sn)){
                                    feicui_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if(twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                }else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                } else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }
                            }
                            4->{
                                if (!oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if(feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }else if(twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                }else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                } else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }

                            }
                            5->{
                                if (!twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if(feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                } else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }

                            }
                            6->{
                                if (!fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if(feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if (twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                } else if (youdao_list.contains(goods.goods_sn)){
                                    youdao_list.remove(goods.goods_sn)
                                }

                            }
                            7->{
                                if (!youdao_list.contains(goods.goods_sn)){
                                    youdao_list.add(goods.goods_sn)
                                }
                                if (you_list.contains(goods.goods_sn)){
                                    you_list.remove(goods.goods_sn)
                                }else if (live_list.contains(goods.goods_sn)){
                                    live_list.remove(goods.goods_sn)
                                }else if(feicui_list.contains(goods.goods_sn)){
                                    feicui_list.remove(goods.goods_sn)
                                }else if(oneYear_list.contains(goods.goods_sn)){
                                    oneYear_list.remove(goods.goods_sn)
                                }else if (twoYear_list.contains(goods.goods_sn)){
                                    twoYear_list.remove(goods.goods_sn)
                                } else if (fiveYear_llist.contains(goods.goods_sn)){
                                    fiveYear_llist.remove(goods.goods_sn)
                                }
                            }
                         }
                        you=""
                        live=""
                        feicui=""
                        oneYear=""
                        twoYear=""
                        fiveYear=""
                        youdao=""
                        if (you_list.size!=0){
                            tv_youji.visibility=View.VISIBLE
                            for (i in 0..you_list.size-1){
                                if (i==0)
                                    you= you_list[i]
                                else
                                    you=you+"/"+you_list[i]
                            }
                            tv_youji.text="邮寄(运费现付):   "+you
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
                                tv_zhibo.text="预约切石直播:   "+live
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
                        if (oneYear_list.size!=0){
                            tv_one.visibility=View.VISIBLE
                            for (i in 0..oneYear_list.size-1){
                                if (i==0){
                                    oneYear=oneYear_list[i]
                                }else{
                                    oneYear=oneYear+"/"+oneYear_list[i]
                                }
                                tv_one.text="一年积分增值:    "+oneYear
                            }

                        }else{
                            tv_one.visibility=View.GONE
                        }
                        if (twoYear_list.size!=0){
                            tv_two.visibility=View.VISIBLE
                            for (i in 0..twoYear_list.size-1){
                                if (i==0){
                                    twoYear=twoYear_list[i]
                                }else{
                                    twoYear=twoYear+"/"+twoYear_list[i]
                                }
                                tv_two.text="两年积分增值:    "+twoYear
                            }

                        }else{
                            tv_two.visibility=View.GONE
                        }
                        if (fiveYear_llist.size!=0){
                            tv_five.visibility=View.VISIBLE
                            for (i in 0..fiveYear_llist.size-1){
                                if (i==0){
                                    fiveYear=fiveYear_llist[i]
                                }else{
                                    fiveYear=fiveYear+"/"+fiveYear_llist[i]
                                }
                                tv_five.text="五年积分增值:    "+fiveYear
                            }

                        }else{
                            tv_five.visibility=View.GONE
                        }
                        if (youdao_list.size!=0){
                            tv_youji_dao.visibility=View.VISIBLE
                            for (i in 0..youdao_list.size-1){
                                if (i==0){
                                    youdao=youdao_list[i]
                                }else{
                                    youdao=youdao+"/"+youdao_list[i]
                                }
                                tv_youji_dao.text="邮寄(运费到付):    "+youdao
                            }

                        }else{
                            tv_youji_dao.visibility=View.GONE
                        }

                        dialog.dismiss()
                        Log.i("you",you)
                        Log.i("live",live)
                        Log.i("oneYear",oneYear)
                        Log.i("twoYear",twoYear)
                        Log.i("fiveYear",fiveYear+fiveYear_llist.size)
                        Log.i("feicui",feicui)
                        Log.i("youdao",youdao+youdao_list.size)
                        getPresenter().getOrderInfo(Constants.getToken(),you,live,feicui,oneYear,twoYear,fiveYear,youdao)
                    }

                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getPresenter().isSetting(Constants.getToken())

    }

    /**
     * 初始化事件
     */
    var indexs=0
    override fun initEvent() {
        tv_submint.setOnClickListener{
            var dialog=PayDialog(this)
            if (you_list.size+live_list.size+feicui_list.size+oneYear_list.size+twoYear_list.size+fiveYear_llist.size+youdao_list.size!=adapter.data.size){
                showToast("请选择购买方式")
            } else if (order?.order!!.user_address.ua_id==null){
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
                                getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"1","",intent.getStringExtra("sb_id"),oneYear,twoYear,fiveYear,youdao)
                            }
                            2->{
                                if (add_address.visibility==View.VISIBLE){
                                    showToast("请添加地址")
                                }else{
                                    indexs=2
                                    showLoadingDialog()
                                    getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"2","",intent.getStringExtra("sb_id"),oneYear,twoYear,fiveYear,youdao)
                                }


                            }
                            3->{
                                indexs=3
                                if (isSetting==1){
                                    showDialog()
                                }else{
                                    PassswordActivity.startPassswordActivity(this@ImOrderActivity,2)
                                }

                            }
                            4->{//他人代付
                                DaifuActivity.startDaifuActivity(mContext,intent.getStringExtra("sb_id"),you,youdao,live,feicui,oneYear,twoYear,fiveYear,order?.order?.user_address?.ua_id!!)
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
//            AddressActivity.startAddressActivity(this)
            var intent=Intent(this,AddressActivity::class.java)
            intent.putExtra("flag",1)
            this.startActivityForResult(intent,2)
        }
    }

    var order:ImOrder?=null
    override fun imOrder(imOrder: ImOrder) {
        loading.visibility=View.GONE
        order=imOrder
        adapter.setNewData(imOrder.order.goods_info)
        for (i in 0..imOrder.order.goods_info.size-1){
            you_list.add(imOrder.order.goods_info[i].goods_sn)
            if (i==0)
                you= you_list[i]
            else
                you=you+"/"+you_list[i]
        }
        tv_youji.visibility=View.VISIBLE
        tv_youji.text="邮寄(运费现付):   "+you
        tv_wuliu.text="物流费用:  ￥"+imOrder.order.shipping_fee
        if (imOrder.order.user_address.address.isNullOrEmpty()){
            add_address.visibility=View.VISIBLE
            tv_address.visibility=View.GONE
            tv_content.visibility=View.GONE
        }else{
            tv_content.visibility=View.VISIBLE
            tv_address.visibility=View.VISIBLE
            tv_content.text=imOrder.order.user_address.consigner+"       "+imOrder.order.user_address.phone
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
        var bt_quxiao=view.findViewById<TextView>(R.id.bt_quxiao)
        var tv_price=view.findViewById<TextView>(R.id.tv_price)
        if (orderInfo==null){
            tv_price.text=order?.order?.total_fee
        }else{
            tv_price.text=orderInfo?.total_fee
        }
        bt_quxiao.setOnClickListener{
            dialog!!.dismiss()
        }
        psw_view.setOnInputFinishListener{
            password=it
            showLoadingDialog()
            getPresenter().pay(Constants.getToken(),you,live,feicui,""+order?.order!!.user_address.ua_id,"3",password!!.md5Salt(),intent.getStringExtra("sb_id"),oneYear,twoYear,fiveYear,youdao)
        }
        dialog = AlertDialog.Builder(mContext).create()
        dialog!!.setView(view)
        dialog!!.show()
    }
    var name=""
    var phone=""
    var address=""
    var id=""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==2&&resultCode==3){
            name=data?.getStringExtra("name")!!
            phone=data?.getStringExtra("phone")
            address=data?.getStringExtra("address")
            id=data?.getStringExtra("id")
            tv_content.text=name+"       "+phone
            add_address.visibility=View.GONE
            tv_content.visibility=View.VISIBLE
            tv_address.visibility=View.VISIBLE
            tv_address.text=address
            order?.order?.user_address?.ua_id=id
        }else if (requestCode==2&&resultCode==4){
            id=data?.getStringExtra("id")!!
            if (id.equals(order?.order!!.user_address.ua_id)){
                add_address.visibility=View.VISIBLE
                tv_content.visibility=View.GONE
                add_address.visibility=View.GONE
            }
        }
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
