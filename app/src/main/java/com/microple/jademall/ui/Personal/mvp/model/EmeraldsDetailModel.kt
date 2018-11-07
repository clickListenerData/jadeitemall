package com.microple.jademall.ui.Personal.mvp.model


import com.microple.jademall.bean.EmeraldsDetail
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.EmeraldsDetailContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class EmeraldsDetailModel: EmeraldsDetailContract.Model {
    override fun jifenEx(token: String, incr_id: String)=AppApi.Api().getjifenEx(token,incr_id)
    override fun getjifenDetail(token: String, incr_id: String)=AppApi.Api().getjifenDetail(token,incr_id)

    override fun exchange(token: String, ct_id: String): Observable<BaseResponseEntity<Any>> =AppApi.Api().exchange(token,ct_id)

    override fun getEmeraldsDetail(token:String,ct_id: String): Observable<BaseResponseEntity<EmeraldsDetail>> =AppApi.Api().getEmeraldsDetail(token,ct_id)
}