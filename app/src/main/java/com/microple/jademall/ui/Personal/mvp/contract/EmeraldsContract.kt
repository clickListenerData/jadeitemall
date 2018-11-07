package com.microple.jademall.ui.Personal.mvp.contract

import com.microple.jademall.bean.Emeralds
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
interface EmeraldsContract {
    interface View :BaseMvpView{
        fun getEmeralds(emeralds: Emeralds)

    }
    interface Model{
        fun getEmeralds(token:String): Observable<BaseResponseEntity<Emeralds>>
        fun getjifen(token: String): Observable<BaseResponseEntity<Emeralds>>


    }
    abstract class Presenter:BaseMvpPresenter<Model, View>(){
        abstract fun getEmeralds(token:String)
        abstract  fun getjifen(token: String)
    }
}