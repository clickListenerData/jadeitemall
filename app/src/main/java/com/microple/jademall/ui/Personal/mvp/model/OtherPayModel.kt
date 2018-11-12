package com.microple.jademall.ui.Personal.mvp.model

import com.microple.jademall.bean.AboutMe
import com.microple.jademall.bean.Pay
import com.microple.jademall.bean.PayTyle
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.AboutMeContract
import com.microple.jademall.ui.Personal.mvp.contract.OtherPayContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class OtherPayModel: OtherPayContract.Model {
    override fun getPayTyle(token: String, type: String)=AppApi.Api().payTyle(token,type)

    override fun otherPay(token: String, pay_type: String, sb_id: String, send: String, live: String, cabinet: String, freight_pay: String, incr_type1: String, incr_type2: String, incr_type3: String, address_id: String, userId: String, trade_password: String)=
            AppApi.Api().otherPay(token,pay_type,sb_id,send,live,cabinet,freight_pay,incr_type1,incr_type2,incr_type3,address_id,userId,trade_password)


}