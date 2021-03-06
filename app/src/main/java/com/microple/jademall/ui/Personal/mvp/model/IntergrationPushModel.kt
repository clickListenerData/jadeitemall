package com.microple.jademall.ui.Personal.mvp.model


import com.microple.jademall.bean.AccountIinfo
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.IntergrationPushContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class IntergrationPushModel: IntergrationPushContract.Model {
    override fun pushInfo(token: String, phone: String)=AppApi.Api().getAccountInfo(token,phone)

    override fun getAccout(token: String): Observable<BaseResponseEntity<AccountIinfo>> =AppApi.Api().getAccount(token)

    override fun push(token: String, to_user: String, points: String,frozen_points:String,trade_password:String): Observable<BaseResponseEntity<Any>> =AppApi.Api().push(token,to_user,points,frozen_points,trade_password)
}