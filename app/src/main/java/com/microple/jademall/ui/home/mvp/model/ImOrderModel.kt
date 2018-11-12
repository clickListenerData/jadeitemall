package com.microple.jademall.ui.home.mvp.model

import com.microple.jademall.bean.*
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.home.mvp.contract.GoodsDetailContract
import com.microple.jademall.ui.home.mvp.contract.ImOrderContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class ImOrderModel: ImOrderContract.Model {
    override fun getPayTyle(token: String,type:String)=AppApi.Api().payTyle(token,type)

    override fun getBuyTyle(token: String, type: String)=AppApi.Api().buyTyle(token,type)

    override fun getOrderInfo(token: String, send: String, live: String, cabinet: String,incr_type1:String,incr_type2:String,incr_type3:String,freight_pay:String): Observable<BaseResponseEntity<OrderInfo>> =AppApi.Api().getOrderInfo(token,send,live,cabinet,incr_type1,incr_type2,incr_type3,freight_pay)

    override fun isSetting(token: String): Observable<BaseResponseEntity<IsSettingPayPW>> =AppApi.Api().isSettingPayPW(token)

    override fun pay(token: String, send: String, live: String, cabinet: String, address_id: String, pay_type: String,trade_password:String,sb_id:String,incr_type1:String,incr_type2:String,incr_type3:String,freight_pay:String): Observable<BaseResponseEntity<Pay>> =
            AppApi.Api().pay(token,send,live,cabinet,address_id,pay_type,trade_password,sb_id,incr_type1,incr_type2,incr_type3,freight_pay)

    override fun imOrder(token: String, sb_id: String, goods_id: String): Observable<BaseResponseEntity<ImOrder>> =
            AppApi.Api().imOrder(token,sb_id,goods_id)
}