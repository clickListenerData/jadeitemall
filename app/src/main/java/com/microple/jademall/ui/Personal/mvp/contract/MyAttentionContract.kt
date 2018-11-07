package com.microple.jademall.ui.Personal.mvp.contract

import com.microple.jademall.bean.AboutMe
import com.microple.jademall.bean.Attention
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
interface MyAttentionContract {
    interface View :BaseMvpView{
        fun getAttention(attention: Attention)

    }
    interface Model{
        fun getAttention(token:String):Observable<BaseResponseEntity<Attention>>

    }
    abstract class Presenter:BaseMvpPresenter<Model, View>(){
        abstract fun getAttention()

    }
}