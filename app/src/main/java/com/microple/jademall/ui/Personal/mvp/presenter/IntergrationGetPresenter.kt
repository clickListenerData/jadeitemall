package com.microple.jademall.ui.Personal.mvp.presenter

import com.microple.jademall.ui.Personal.mvp.contract.IntergrationGetContract
import com.microple.jademall.ui.Personal.mvp.model.IntergrationGetModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class IntergrationGetPresenter:IntergrationGetContract.Presenter() {
    override fun getProfit(token: String, incr_id: String, goods_id: String) {
        getModel().getProfit(token,incr_id,goods_id).ui({
                getView()?.getProfit(it.data!!)
        },{
                getView()?.showToast(it)
        })
    }

    override fun createModel(): IntergrationGetContract.Model =IntergrationGetModel()
}