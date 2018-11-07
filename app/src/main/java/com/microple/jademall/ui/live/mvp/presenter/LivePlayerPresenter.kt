package com.microple.jademall.ui.live.mvp.presenter

import com.microple.jademall.ui.live.mvp.contract.LivePlayerContract
import com.microple.jademall.ui.live.mvp.model.LivePlayerModel
import com.microple.jademall.uitls.showToast
import com.weibiaogan.litong.extensions.ui

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class LivePlayerPresenter:LivePlayerContract.Presenter() {
    override fun follow(token: String, supplier_id: String) {
        getModel().follow(token,supplier_id).ui({
            getView()?.follow()
        },{
            getView()?.showToast(it.message)
        })
    }

    override fun isFollow(token: String, supplier_id: String) {
        getModel().isFollow(token,supplier_id).ui({
            getView()?.isFollow(it.data!!)
        },{
            getView()?.showToast(it.message)
        })
    }

    override fun getGoods(live_id: String) {
        getModel().getGoods(live_id).ui({
            getView()?.getGoods(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun liveShare(live_id: String) {
        getModel().liveShare(live_id).ui({
            getView()?.liveShare(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun createModel(): LivePlayerContract.Model =LivePlayerModel()
}