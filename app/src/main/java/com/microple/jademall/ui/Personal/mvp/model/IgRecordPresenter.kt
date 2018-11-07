package com.microple.jademall.ui.Personal.mvp.model

import com.microple.jademall.common.Constants
import com.microple.jademall.ui.Personal.mvp.contract.IgRecordContract
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
class IgRecordPresenter:IgRecordContract.Presenter() {
    override fun igRecord() {
        getView()?.showLoadingDialog()
        getModel().igRecord(Constants.getToken()).ui({
            getView()?.igRecord(it.data!!)
            getView()?.dismissLoadingDialog()
        },{
            getView()?.showToast(it.message)
            getView()?.dismissLoadingDialog()
        })
    }

    override fun createModel(): IgRecordContract.Model =IgRecordModel()
}