package com.microple.jademall.ui.Personal.mvp.contract

import com.microple.jademall.bean.Pay
import com.microple.jademall.bean.PayTyle
import com.microple.jademall.bean.Pick
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
interface PickGoodsContract {
    interface View :BaseMvpView{
        fun pick(pick:Pick)
        fun pay(pay: Pay)
        fun getPayTyle(payTyle: PayTyle)
    }
    interface Model{
        fun pick(token:String,ct_id:String): Observable<BaseResponseEntity<Pick>>
        fun pay(token:String,pay_type:String,address_id:String,cabinet_id:String,shipping_pay:String):Observable<BaseResponseEntity<Pay>>
        fun getPayTyle(token: String,type:String):Observable<BaseResponseEntity<PayTyle>>
    }
    abstract class Presenter:BaseMvpPresenter<Model, View>(){
        abstract fun pick(token:String,ct_id:String)
        abstract fun pay(token:String,pay_type:String,address_id:String,cabinet_id:String,shipping_pay:String)
        abstract fun getPayTyle(token: String,type:String)
    }
}