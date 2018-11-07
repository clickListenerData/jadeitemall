package com.microple.jademall.ui.home.mvp.model

import com.microple.jademall.bean.Daifu
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.home.mvp.contract.DaifuContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class DaifuModel: DaifuContract.Model {
    override fun history(token: String)=AppApi.Api().getHistory(token)

    override fun daifu(token: String, sb_id: String, send: String, freight_pay: String, live: String, cabinet: String, incr_type1: String, incr_type2: String, incr_type3: String, address_id: String, phone: String, pay_msg: String)=AppApi.Api().daifu(token,sb_id,send,freight_pay,live,cabinet,incr_type1,incr_type2,incr_type3,address_id,phone,pay_msg)
    override fun pushInfo(token: String, phone: String)=AppApi.Api().getAccountInfo(token,phone)

}