package com.microple.jademall.ui.Personal.mvp.model

import com.microple.jademall.bean.Emeralds
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.EmeraldsContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class EmeraldsModel: EmeraldsContract.Model {
    override fun getjifen(token: String)=AppApi.Api().getjifen(token)

    override fun getEmeralds(token: String): Observable<BaseResponseEntity<Emeralds>> = AppApi.Api().getEmeralds(token)
}