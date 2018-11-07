package com.microple.jademall.ui.Personal.mvp.presenter

import com.microple.jademall.ui.Personal.mvp.contract.PickGoodsContract
import com.microple.jademall.ui.Personal.mvp.model.PickGoodsModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class PickGoodsPresenter:PickGoodsContract.Presenter() {
    override fun pay(token: String, pay_type: String, address_id: String, cabinet_id: String, shipping_pay: String) {
        getModel().pay(token,pay_type,address_id,cabinet_id,shipping_pay).ui({
            getView()?.pay(it.data!!)
        },{
            getView()?.showToast(it.message)
        })
    }


    override fun pick(token: String, ct_id: String) {
        getModel().pick(token,ct_id).ui({
            getView()?.pick(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun createModel(): PickGoodsContract.Model =PickGoodsModel()
}