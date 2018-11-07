package com.microple.jademall.ui.Personal.mvp.contract

import com.microple.jademall.bean.IgRecord
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
interface IgRecordContract {
    interface View :BaseMvpView{
        fun igRecord(igRecord: IgRecord)

    }
    interface Model{
        fun igRecord(token:String): Observable<BaseResponseEntity<IgRecord>>

    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
       abstract  fun igRecord()
    }
}