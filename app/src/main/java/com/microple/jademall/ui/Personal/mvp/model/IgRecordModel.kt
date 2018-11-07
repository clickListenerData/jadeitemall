package com.microple.jademall.ui.Personal.mvp.model

import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.IgRecordContract

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
class IgRecordModel:IgRecordContract.Model {
    override fun igRecord(token:String)=AppApi.Api().igRecord(token)
}