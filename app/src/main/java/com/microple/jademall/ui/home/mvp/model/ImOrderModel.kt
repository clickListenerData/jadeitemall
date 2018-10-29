package com.microple.jademall.ui.home.mvp.model

import com.microple.jademall.bean.ImOrder
import com.microple.jademall.bean.IsSettingPayPW
import com.microple.jademall.bean.OrderInfo
import com.microple.jademall.bean.Pay
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
    override fun getOrderInfo(token: String, send: String, live: String, cabinet: String): Observable<BaseResponseEntity<OrderInfo>> =AppApi.Api().getOrderInfo(token,send,live,cabinet)

    override fun isSetting(token: String): Observable<BaseResponseEntity<IsSettingPayPW>> =AppApi.Api().isSettingPayPW(token)

    override fun pay(token: String, send: String, live: String, cabinet: String, address_id: String, pay_type: String,trade_password:String,sb_id:String,pick_type:String,ct_id:String): Observable<BaseResponseEntity<Pay>> =
            AppApi.Api().pay(token,send,live,cabinet,address_id,pay_type,trade_password,sb_id,pick_type,ct_id)

    override fun imOrder(token: String, sb_id: String, goods_id: String): Observable<BaseResponseEntity<ImOrder>> =
            AppApi.Api().imOrder(token,sb_id,goods_id)
}