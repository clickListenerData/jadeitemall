package com.microple.jademall.ui.Personal.mvp.presenter

import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.mvp.contract.MyAttentionContract
import com.microple.jademall.ui.Personal.mvp.model.MyAttentionModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class MyAttentionPresenter:MyAttentionContract.Presenter(){
    override fun getAttention() {
        getView()?.showLoadingDialog()
        getModel().getAttention(Constants.getToken()).ui({
            getView()?.getAttention(it.data!!)
            getView()?.dismissLoadingDialog()
        },{
            getView()?.showToast(it)
            getView()?.dismissLoadingDialog()
        })
    }

    override fun createModel(): MyAttentionContract.Model =MyAttentionModel()
}