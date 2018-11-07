package com.microple.jademall.ui.Personal.mvp.model

import com.microple.jademall.bean.AboutMe
import com.microple.jademall.bean.Attention
import com.microple.jademall.common.AppApi
import com.microple.jademall.ui.Personal.mvp.contract.AboutMeContract
import com.microple.jademall.ui.Personal.mvp.contract.MyAttentionContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable


/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
class MyAttentionModel: MyAttentionContract.Model {
    override fun getAttention(token: String)=AppApi.Api().getAttention(token)

}