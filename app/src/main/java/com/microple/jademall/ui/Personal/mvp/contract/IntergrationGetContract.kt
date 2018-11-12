package com.microple.jademall.ui.Personal.mvp.contract

import com.microple.jademall.bean.Profit
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
interface IntergrationGetContract {
    interface View :BaseMvpView{
        fun getProfit( profit: Profit)

    }
    interface Model{
        fun getProfit(token:String,incr_id:String,goods_id:String): Observable<BaseResponseEntity<Profit>>
    }
    abstract class Presenter:BaseMvpPresenter<Model, View>(){
        abstract fun getProfit(token:String,incr_id:String,goods_id:String)
    }
}