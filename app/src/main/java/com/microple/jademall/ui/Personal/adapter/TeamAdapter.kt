package com.microple.jademall.ui.Personal.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.microple.jademall.R
import com.microple.jademall.bean.Team
import com.microple.jademall.common.Constant
import com.microple.jademall.uitls.loadHeadImag
import com.microple.jademall.uitls.loadImag

/**
 * author: xiaoguagnfei
 * date: 2018/8/15
 * describe:
 */
class TeamAdapter(data:List<Team.UserListBean>):BaseQuickAdapter<Team.UserListBean,BaseViewHolder>(R.layout.item_team,data) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder?, item: Team.UserListBean?) {
        helper?.setText(R.id.tv_name,item?.user_name    +"ID"+item?.user_id)
        helper?.setText(R.id.tv_phone,item?.phone)
        helper?.setText(R.id.tv_Price,"产生佣金    "+item?.divide_money)
        helper?.getView<ImageView>(R.id.iv_goodsImage)?.loadHeadImag(item?.head_img!!)
    }
}