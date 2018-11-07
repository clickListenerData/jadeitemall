package com.microple.jademall.ui.Personal.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.microple.jademall.R
import com.microple.jademall.bean.IgRecord

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
class IgRecordAdapter(data:List<IgRecord.FrozenInfoBean>):BaseQuickAdapter<IgRecord.FrozenInfoBean,BaseViewHolder>(R.layout.item_igrecode) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder?, item: IgRecord.FrozenInfoBean?) {
        helper?.setText(R.id.tv_time,item?.surplus_time)
                ?.setText(R.id.tv_dongjie,item?.frozen_time)
                ?.setText(R.id.tv_jifen,"${item?.points}积分")

    }
}