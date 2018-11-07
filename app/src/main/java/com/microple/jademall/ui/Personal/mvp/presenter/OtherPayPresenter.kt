package com.microple.jademall.ui.Personal.mvp.presenter

import com.microple.jademall.ui.Personal.mvp.contract.AboutMeContract
import com.microple.jademall.ui.Personal.mvp.contract.OtherPayContract
import com.microple.jademall.ui.Personal.mvp.model.AboutMelModel
import com.microple.jademall.ui.Personal.mvp.model.OtherPayModel
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class OtherPayPresenter:OtherPayContract.Presenter() {
    override fun otherPay(token: String, pay_type: String, sb_id: String, send: String, live: String, cabinet: String, freight_pay: String, incr_type1: String, incr_type2: String, incr_type3: String, address_id: String, userId: String, trade_password: String) {
        getModel().otherPay(token,pay_type,sb_id,send,live,cabinet,freight_pay,incr_type1,incr_type2,incr_type3,address_id,userId,trade_password).ui({
                    getView()?.otherPay(it.data!!)
            getView()?.dismissLoadingDialog()

        },{
            getView()?.showToast(it.message)
            getView()?.dismissLoadingDialog()
        })
    }

    override fun createModel(): OtherPayContract.Model =OtherPayModel()
}