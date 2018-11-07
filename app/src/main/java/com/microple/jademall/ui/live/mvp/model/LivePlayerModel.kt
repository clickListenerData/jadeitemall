package com.microple.jademall.ui.live.mvp.model

import com.microple.jademall.bean.Follow
import com.microple.jademall.bean.LiveDetail
import com.microple.jademall.bean.LiveShare
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.AboutMeContract
import com.microple.jademall.ui.live.mvp.contract.LiveDetailContract
import com.microple.jademall.ui.live.mvp.contract.LivePlayerContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class LivePlayerModel: LivePlayerContract.Model {
    override fun follow(token: String, supplier_id: String)=AppApi.Api().follow(token,supplier_id)

    override fun isFollow(token: String, supplier_id: String)=AppApi.Api().isFollow(token,supplier_id)

    override fun liveShare(live_id: String): Observable<BaseResponseEntity<LiveShare>> =AppApi.Api().liveShare(live_id)
    override fun getGoods(live_id: String) =AppApi.Api().liveGoods(live_id)


}