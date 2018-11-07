package com.microple.jademall.ui.live.mvp.contract

import com.microple.jademall.bean.Follow
import com.microple.jademall.bean.LiveGoods
import com.microple.jademall.bean.LiveList
import com.microple.jademall.bean.LiveShare
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/8/24
 * describe:
 */
interface LivePlayerContract {
    interface View :BaseMvpView{
        fun liveShare(liveShare: LiveShare)
        fun getGoods(liveGoods: LiveGoods)
        fun isFollow(follow: Follow)
        fun follow()

    }
    interface Model{
        fun liveShare(live_id: String): Observable<BaseResponseEntity<LiveShare>>
        fun getGoods(live_id:String): Observable<BaseResponseEntity<LiveGoods>>
        fun follow(token:String,supplier_id:String):Observable<BaseResponseEntity<Any>>
        fun isFollow(token:String,supplier_id:String):Observable<BaseResponseEntity<Follow>>
    }
    abstract class Presenter:BaseMvpPresenter<Model, View>(){
        abstract fun liveShare(live_id: String)
        abstract fun getGoods(live_id:String)
        abstract fun follow(token:String,supplier_id:String)
        abstract fun isFollow(token:String,supplier_id:String)
    }
}