package com.microple.jademall.ui.home.mvp.presenter

import com.microple.jademall.ui.home.mvp.contract.ImOrderContract
import com.microple.jademall.ui.home.mvp.model.ImOrderModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class ImOrderPresenter:ImOrderContract.Presenter() {
    override fun getPayTyle(token: String,type:String) {
        getModel().getPayTyle(token,type).ui({
            getView()?.getPayTyle(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun getBuyTyle(token: String, type: String) {
        getModel().getBuyTyle(token,type).ui({
            getView()?.getBuyTyle(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun getOrderInfo(token: String, send: String, live: String, cabinet: String,incr_type1:String,incr_type2:String,incr_type3:String,freight_pay:String) {
        getModel().getOrderInfo(token,send,live,cabinet,incr_type1,incr_type2,incr_type3,freight_pay).ui({
            getView()?.getOrderInfo(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun isSetting(token: String) {
        getModel().isSetting(token).ui({
                getView()?.isSetting(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun pay(token: String, send: String, live: String, cabinet: String, address_id: String, pay_type: String,trade_password:String,sb_id:String,incr_type1:String,incr_type2:String,incr_type3:String,freight_pay:String) {
        getModel().pay(token,send,live,cabinet,address_id,pay_type,trade_password,sb_id,incr_type1,incr_type2,incr_type3,freight_pay).ui({
            getView()?.pay(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun imOrder(token: String, sb_id: String, goods_id: String) {
            getModel().imOrder(token,sb_id,goods_id).ui({
                getView()?.imOrder(it.data!!)
            },{
                getView()?.showToast(it)
            })
    }

    override fun createModel(): ImOrderContract.Model =ImOrderModel()
}